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
import com.example.multimodulepractice.common.data.models.local.City
import com.example.multimodulepractice.common.data.models.local.GeoPoint
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.utils.calculateDistance
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.main.impl.data.interactors.MapInteractor
import com.example.multimodulepractice.main.impl.databinding.ClusteredViewBinding
import com.example.multimodulepractice.main.impl.domain.MapScreenRepository
import com.example.multimodulepractice.main.impl.ui.map.MapUiState.MapState
import com.example.multimodulepractice.main.impl.utils.cityTextStyle
import com.example.multimodulepractice.main.impl.utils.landmarkTextStyle
import com.example.multimodulepractice.main.impl.utils.toGeoPoint
import com.example.multimodulepractice.main.impl.utils.toMapKitPoint
import com.filters.api.data.FiltersRepository
import com.filters.api.data.models.Filters
import com.main.common.data.local.map.MapLandmark
import com.splash.api.domain.CitiesRepository
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.logo.Padding
import com.yandex.mapkit.map.CameraListener
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor,
    private val geoRepository: GeoRepository,
    @AppContext
    private val context: Context,
    private val filtersRepository: FiltersRepository,
    private val citiesRepository: CitiesRepository,
    private val mapScreenRepository: MapScreenRepository,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    val map = MapView(context)
    private val camera: CameraPosition
        get() = map.mapWindow.map.cameraPosition

    private var userMapObject: PlacemarkMapObject? = null

    private val tapListeners = mutableListOf<MapObjectTapListener>()
    private val objectsCollection = map.mapWindow.map.mapObjects.addCollection()
    private val citiesCollection = map.mapWindow.map.mapObjects.addCollection()

    //TODO remove to cities
    private val mapObjects = mutableListOf<PlacemarkMapObject>()
    private val cityObjects = mutableListOf<PlacemarkMapObject>()
    private var boundary: PolygonMapObject? = null
    private val boundaryPolygonList: MutableList<LinearRing> = ArrayList()

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

    private val cameraListener = CameraListener { _, cameraPosition, _, _ ->
        mapInfo(cameraPosition.target.toGeoPoint(), cameraPosition.zoom)
        invalidateObjectsByZoom(cameraPosition.zoom)
    }

    private var mapRequestJob: MapInfoJob = MapInfoJob()

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY())
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    private var clusteredCollection: ClusterizedPlacemarkCollection =
        objectsCollection.addClusterizedPlacemarkCollection(clusterListener)

    init {
        onStart()
        subscribeToCities()
        subscribeToGeo()
        subscribeToFilters()
        map.mapWindow.map.addCameraListener(cameraListener)
        subscribeToMapScreen()
    }

    private fun subscribeToMapScreen() {
        viewModelScope.launch {
            mapScreenRepository.isMapOpen.collectLatest { isOpen ->
                _uiStateFlow.update {
                    it.copy(isMapOpen = isOpen)
                }
            }
        }
    }

    private fun subscribeToCities() {
        viewModelScope.launch {
            citiesRepository.citiesFlow().drop(1).take(1).collect {
                it.forEach { city ->
                    cityObjects.add(createCityObject(city))
                }
                mapInfo(camera.target.toGeoPoint(), camera.zoom)
            }
        }
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

    private fun subscribeToGeo() {
        viewModelScope.launch {
            geoRepository.geoInfoFlow().collectLatest { geoInfo ->
                updateUserLocation(geoPoint = geoInfo.currentPoint)
            }
        }
    }

    private fun invalidateObjectsByZoom(zoom: Float) {
        when {
            zoom > 12f -> {
                objectsCollection.setVisible(true, Animation(Animation.Type.LINEAR, 500f), null)
                citiesCollection.setVisible(false, Animation(Animation.Type.LINEAR, 500f), null)
            }

            else -> {
                objectsCollection.setVisible(false, Animation(Animation.Type.LINEAR, 500f), null)
                citiesCollection.setVisible(true, Animation(Animation.Type.LINEAR, 500f), null)
            }
        }
    }

    private fun mapInfo(point: GeoPoint, zoom: Float) {
        if (zoom < 12f) return
        citiesRepository.closestCity(point)?.let { city ->
            if (mapRequestJob.city != city) {
                _uiStateFlow.update {
                    it.copy(state = MapState.Loading)
                }
                mapRequestJob.job?.cancel()
                val job = viewModelScope.launch {
                    when (val result = runWithMinTime({ interactor.getMapInfo(city) })) {
                        is ResponseState.Success -> {
                            _uiStateFlow.update { it.copy(state = MapState.Content) }
                            drawBoundary(result.data.city.points)
                            addAttractions(result.data.list)
                            citiesRepository.loadedCity(city)
                        }

                        is ResponseState.Error -> {
                            mapRequestJob = MapInfoJob()
                            delay(3000)
                            mapInfo(point, camera.zoom)
                        }
                    }
                }
                mapRequestJob = mapRequestJob.copy(job = job)
            }
        }
    }

    private fun addAttractions(list: List<MapLandmark>) {
        val filters = filtersRepository.filters.value ?: filtersRepository.zeroFilters
        for (landmark in list) {
            mapObjects.add(createLandmarkObject(landmark))
        }
        invalidateMapObjects(filters)
        clusteredCollection.clusterPlacemarks(60.0, 15)
    }

    private fun createLandmarkObject(landmark: MapLandmark): PlacemarkMapObject {
        return clusteredCollection.addPlacemark().apply {
            geometry = landmark.geoPoint.toMapKitPoint()
            val textStyle = landmarkTextStyle(landmark.color)
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
            userData = landmark
        }
    }

    private fun createCityObject(city: City): PlacemarkMapObject {
        return citiesCollection.addPlacemark().apply {
            geometry = city.geoPoint.toMapKitPoint()
            setText(city.name, cityTextStyle)

            MapObjectTapListener { _, _ ->
                onMapAction(MapActions.OnCityTapped(city.geoPoint))
                true
            }.also {
                tapListeners.add(it)
                addTapListener(it)
            }

            viewModelScope.launch(Dispatchers.IO) {
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data(city.icon)
                    .error(R.drawable.ic_city_pin)
                    .fallback(R.drawable.ic_city_pin)
                    .allowHardware(false)
                    .build()

                val bitmap = (loader.execute(request).drawable as BitmapDrawable).bitmap
                withContext(Dispatchers.Main) {
                    setIcon(ImageProvider.fromBitmap(bitmap))
                }
            }
        }
    }

    private fun invalidateMapObjects(filters: Filters) {
        val distance = filters.distance.km
        val filterCategories = filters.categories

        val mapObjectsCurrent = ArrayList(mapObjects)

        for (item in mapObjectsCurrent.withIndex()) {
            val placeMark = item.value
            val landmark = item.value.userData as MapLandmark

            try {
                clusteredCollection.remove(placeMark)
            } catch (_: Exception) {
            }

            if (calculateDistance(
                    placeMark.geometry.toGeoPoint(),
                    geoRepository.geoInfoImmediately().currentPoint
                ) <= distance && landmark.categoryIds.any { id -> filterCategories.any { it.id == id && it.isSelected } }
            ) {
                mapObjects[item.index] = createLandmarkObject(landmark)
            }
        }
        clusteredCollection.clusterPlacemarks(60.0, 15)
    }

    fun onMapAction(action: MapActions) {
        when (action) {

            is MapActions.OnPlaceMarkTapped -> {
                viewModelScope.launch {
                    _uiEvent.send(MapUiEvent.OnAttractionOpen(action.landmarkId))
                }
            }

            is MapActions.OnCityTapped -> {
                moveToLocation(action.geoPoint, true, 13f)
            }

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
            userMapObject = objectsCollection.addPlacemark().apply {
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

    private fun moveToLocation(geoPoint: GeoPoint, isAnimated: Boolean, zoomValue: Float = 14.5f) {
        val location = geoPoint.toMapKitPoint()
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
        boundary?.let(objectsCollection::remove)
        val outerBoundaryCoordinates = listOf(
            Point(89.0, -179.0),
            Point(89.0, 179.0),
            Point(-89.0, 179.0),
            Point(-89.0, -179.0)
        )

        boundaryPolygonList.add(LinearRing(points.map { it.toMapKitPoint() }))
        val polygon = Polygon(
            LinearRing(outerBoundaryCoordinates),
            boundaryPolygonList
        )
        boundary = objectsCollection.addPolygon(polygon).apply {
            fillColor = Color.argb(50, 255, 0, 0)
            strokeColor = Color.argb(100, 255, 0, 0)
            strokeWidth = 2.0f
        }
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