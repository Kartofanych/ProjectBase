package com.example.multimodulepractice.main.impl.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.models.local.GeoPoint
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.main.impl.data.interactors.MapInteractor
import com.example.multimodulepractice.main.impl.data.local_models.map.MapLandmark
import com.example.multimodulepractice.main.impl.databinding.ClusteredViewBinding
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.ui.map.MapUiState.MapState
import com.example.multimodulepractice.main.impl.utils.iconTextStyle
import com.example.multimodulepractice.main.impl.utils.toGeoPoint
import com.example.multimodulepractice.main.impl.utils.toMapKitPoint
import com.filters.api.data.FiltersRepository
import com.filters.api.data.models.Filters
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.logo.Padding
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.ClusterListener
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectDragListener
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@AppScope
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor,
    private val geoRepository: GeoRepository,
    @AppContext
    private val context: Context,
    private val attractionRepository: AttractionRepository,
    private val filtersRepository: FiltersRepository
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    val map = MapView(context)

    private var userMapObject: PlacemarkMapObject? = null

    private val tapListeners = mutableListOf<MapObjectTapListener>()
    private val mapObjects = mutableListOf<Pair<PlacemarkMapObject, MapLandmark>>()

    private val clusterListener = ClusterListener { cluster ->
        val count = cluster.placemarks.map { it.isVisible }.count()
        cluster.appearance.setView(
            ViewProvider(
                ClusteredViewBinding.inflate(LayoutInflater.from(context)).root.also {
                    it.findViewById<TextView>(R.id.count).text = count.toString()
                }
            )
        )
        cluster.appearance.zIndex = 100f
    }

    private val pinDragListener = object : MapObjectDragListener {
        override fun onMapObjectDragStart(p0: MapObject) = Unit

        override fun onMapObjectDrag(p0: MapObject, p1: Point) = Unit

        override fun onMapObjectDragEnd(p0: MapObject) {
            clusteredCollection.clusterPlacemarks(60.0, 15)
        }
    }

    private val collection = map.mapWindow.map.mapObjects.addCollection()

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY())
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    private var clusteredCollection: ClusterizedPlacemarkCollection =
        collection.addClusterizedPlacemarkCollection(clusterListener)

    init {
        onStart()
        launch()
        subscribeToGeo()
        subscribeToFilters()
    }

    private fun subscribeToFilters() {
        viewModelScope.launch {
            filtersRepository.filters.filterNotNull().drop(1).collectLatest { filters ->
                when (filters) {
                    filtersRepository.zeroFilters -> _uiStateFlow.update {
                        it.copy(isFiltersDefault = true)
                    }

                    else -> _uiStateFlow.update {
                        it.copy(isFiltersDefault = false)
                    }
                }
                invalidateMapObjects(filters)
            }
        }
    }

    private fun launch() {
        _uiStateFlow.update {
            it.copy(state = MapState.Loading)
        }
        viewModelScope.launch {
            when (val result = runWithMinTime(interactor::getMapInfo)) {
                is ResponseState.Success -> {
                    _uiStateFlow.update { it.copy(state = MapState.Content) }
                    drawBoundary(result.data.city.points)
                    addAttractions(result.data.list, result.data.filters)
                    filtersRepository.setDefaultFilters(result.data.filters)
                }

                is ResponseState.Error -> {
                    _uiStateFlow.update { it.copy(state = MapState.Error) }
                }
            }
        }
    }

    private fun addAttractions(list: List<MapLandmark>, filters: Filters) {
        for (landmark in list) {
            mapObjects.add(addObjectToCollection(landmark) to landmark)
        }
        invalidateMapObjects(filters)
        clusteredCollection.clusterPlacemarks(60.0, 15)
    }

    private fun subscribeToGeo() {
        viewModelScope.launch {
            geoRepository.geoInfoFlow().collectLatest { geoInfo ->
                updateUserLocation(geoPoint = geoInfo.currentPoint)
            }
        }
    }

    private fun addObjectToCollection(landmark: MapLandmark): PlacemarkMapObject {
        return clusteredCollection.addPlacemark().apply {
            geometry = landmark.geoPoint.toMapKitPoint()
            val textStyle = iconTextStyle(landmark.color)
            setText(landmark.name, textStyle)

            MapObjectTapListener { _, _ ->
                onMapAction(MapActions.OnPlaceMarkTapped(landmark.id))
                true
            }.also {
                tapListeners.add(it)
                addTapListener(it)
            }

            viewModelScope.launch(Dispatchers.IO) {
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data(landmark.icon)
                    .error(R.drawable.ic_dollar_pin)
                    .fallback(R.drawable.ic_dollar_pin)
                    .allowHardware(false)
                    .build()

                val result = loader.execute(request).drawable
                val bitmap = (result as BitmapDrawable).bitmap
                withContext(Dispatchers.Main) {
                    setIcon(ImageProvider.fromBitmap(bitmap))
                }
            }
            isDraggable = true
            setDragListener(pinDragListener)
        }
    }

    private fun invalidateMapObjects(filters: Filters) {
        val distance = filters.distance.km
        val filterCategories = filters.categories

        val mapObjectsCurrent = ArrayList(mapObjects)

        for (item in mapObjectsCurrent.withIndex()) {
            val placeMark = item.value.first
            val landmark = item.value.second

            try {
                clusteredCollection.remove(placeMark)
            } catch (e: Exception) {
            }

            if (calculateDistance(
                    placeMark.geometry.toGeoPoint(),
                    geoRepository.geoInfoImmediately().currentPoint
                ) <= distance && landmark.categoryIds.any { id -> filterCategories.any { it.id == id && it.isSelected } }
            ) {
                mapObjects[item.index] =
                    mapObjects[item.index].copy(first = addObjectToCollection(landmark))
            }
        }
        clusteredCollection.clusterPlacemarks(60.0, 15)
    }

    fun onMapAction(action: MapActions) {
        when (action) {

            is MapActions.OnPlaceMarkTapped -> {
                attractionRepository.getLandmark(action.landmarkId)
            }

            MapActions.OnRelaunchMap -> launch()

            MapActions.OnFiltersOpen -> {
                viewModelScope.launch {
                    _uiEvent.send(MapUiEvent.OnFiltersOpen)
                }
            }

            MapActions.OnMyLocationClick -> {
                moveToLocation(geoRepository.geoInfoImmediately().currentPoint, true)
            }
        }
    }

    private fun updateUserLocation(geoPoint: GeoPoint) {
        val location = geoPoint.toMapKitPoint()
        if (userMapObject == null) {
            userMapObject = collection.addPlacemark().apply {
                geometry = location
                setIcon(
                    ImageProvider.fromBitmap(
                        getDrawable(
                            context,
                            R.drawable.ic_me_pin
                        )!!.toBitmap()
                    )
                )
            }
            moveToLocation(geoPoint, false)
        } else {
            userMapObject?.let {
                it.geometry = location
            }
        }
    }

    private fun moveToLocation(geoPoint: GeoPoint, isAnimated: Boolean) {
        val location = geoPoint.toMapKitPoint()
        val zoomValue = 16.5f
        when {
            !isAnimated -> map.mapWindow.map.move(CameraPosition(location, zoomValue, 0.0f, 0.0f))
            else -> map.mapWindow.map.move(
                CameraPosition(location, zoomValue, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0.5f),
                null
            )
        }
    }

    private fun drawBoundary(points: List<GeoPoint>) {
        val mapObjects = map.mapWindow.map.mapObjects.addCollection()
        val outerBoundaryCoordinates = listOf(
            Point(89.0, -170.0),
            Point(89.0, 170.0),
            Point(-89.0, 170.0),
            Point(-89.0, -170.0)
        )

        val polygon = Polygon(
            LinearRing(outerBoundaryCoordinates),
            listOf(LinearRing(points.map { it.toMapKitPoint() }))
        )
        val polygonMapObject: PolygonMapObject = mapObjects.addPolygon(polygon)

        val fillColor = Color.argb(50, 255, 0, 0)
        polygonMapObject.fillColor = fillColor

        val strokeColor = Color.argb(100, 255, 0, 0)
        polygonMapObject.strokeColor = strokeColor

        polygonMapObject.strokeWidth = 2.0f
    }

    private fun calculateDistance(geoPoint1: GeoPoint, geoPoint2: GeoPoint): Double {
        val r = 6371.0
        val dLat = Math.toRadians(geoPoint2.lat.toDouble() - geoPoint1.lat.toDouble())
        val dLon = Math.toRadians(geoPoint2.lon.toDouble() - geoPoint1.lon.toDouble())
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(geoPoint1.lat.toDouble())) * cos(Math.toRadians(geoPoint2.lat.toDouble())) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    private fun onStart() {
        MapKitFactory.getInstance().onStart()
        map.onStart()
        val style = "[" +
                "        {" +
                "            \"types\": \"point\"," +
                "            \"tags\": {" +
                "                \"all\": [" +
                "                    \"poi\"" +
                "                ]" +
                "            }," +
                "            \"stylers\": {" +
                "                \"visibility\": \"off\"" +
                "            }" +
                "        }" +
                "    ]"
        map.mapWindow.map.setMapStyle(style)
        map.mapWindow.map.logo.setPadding(Padding(6, 12))
    }

    private fun onStop() {
        map.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onCleared() {
        onStop()
        super.onCleared()
    }
}