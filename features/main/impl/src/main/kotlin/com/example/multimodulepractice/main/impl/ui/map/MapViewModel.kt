package com.example.multimodulepractice.main.impl.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.models.local.GeoPoint
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.data.interactors.MapInteractor
import com.example.multimodulepractice.main.impl.data.local_models.map.MapLandmark
import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.ui.map.MapUiState.MapState
import com.example.multimodulepractice.main.impl.utils.iconTextStyle
import com.example.multimodulepractice.main.impl.utils.toMapKitPoint
import com.example.multimodulepractice.main.impl.utils.toGeoPoint
import com.filters.api.data.FiltersRepository
import com.filters.api.data.models.Filters
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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

@MainScope
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor,
    private val geoRepository: GeoRepository,
    @AppContext
    private val context: Context,
    private val attractionRepository: AttractionRepository,
    private val filtersRepository: FiltersRepository
) : ViewModel() {

    private var userMapObject: PlacemarkMapObject? = null

    private val tapListeners = mutableListOf<MapObjectTapListener>()
    private val mapObjects = mutableListOf<Pair<PlacemarkMapObject, MapLandmark>>()

    @SuppressLint("StaticFieldLeak")
    val map = MapView(context)

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY())
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    init {
        subscribeToGeo()
        subscribeToFilters()
        launch()
    }

    private fun subscribeToFilters() {
        viewModelScope.launch {
            filtersRepository.filters.filterNotNull().collectLatest { filters ->
                when (filters) {
                    filtersRepository.zeroFilters -> _uiStateFlow.update {
                        it.copy(isFiltersDefault = true)
                    }

                    else -> _uiStateFlow.update {
                        it.copy(isFiltersDefault = false)
                    }
                }
                val distance = filters.distance.km
                val filterCategories = filters.categories
                for (item in mapObjects) {
                    val placeMark = item.first
                    val landmark = item.second
                    if (calculateDistance(
                            placeMark.geometry.toGeoPoint(),
                            geoRepository.geoInfoImmediately().currentPoint
                        ) > distance || !landmark.categoryIds.any { id -> filterCategories.any { it.id == id && it.isSelected } }
                    ) {
                        placeMark.setVisible(false, Animation(Animation.Type.SMOOTH, 200f), null)
                    } else {
                        placeMark.setVisible(true, Animation(Animation.Type.SMOOTH, 200f), null)
                    }
                }
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
            val mapObjectCollection = map.mapWindow.map.mapObjects
            mapObjectCollection.addPlacemark().apply {
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
                if (!landmark.categoryIds.any { id -> filters.categories.any { it.id == id && it.isSelected } }) {
                    isVisible = false
                }
                mapObjects.add(this to landmark)
            }
        }
    }

    private fun subscribeToGeo() {
        viewModelScope.launch {
            geoRepository.geoInfoFlow().collectLatest { geoInfo ->
                updateUserLocation(geoPoint = geoInfo.currentPoint)
            }
        }
    }

    fun onMapAction(action: MapActions) {
        when (action) {

            is MapActions.OnPlaceMarkTapped -> {
                attractionRepository.getLandmark(action.landmarkId)
            }

            MapActions.OnMapStarted -> onStart()

            MapActions.OnMapStopped -> onStop()

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
            val mapObjectCollection = map.mapWindow.map.mapObjects

            userMapObject = mapObjectCollection.addPlacemark().apply {
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
    }

    private fun onStop() {
        map.onStop()
    }
}