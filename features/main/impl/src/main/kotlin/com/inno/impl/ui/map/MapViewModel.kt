package com.inno.impl.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.common.models.local.GeoPoint
import com.example.common.models.local.ResponseState
import com.example.common.utils.runWithMinTime
import com.example.multimodulepractice.main.impl.R
import com.inno.geo.repository.GeoRepository
import com.inno.impl.data.interactors.MapInteractor
import com.inno.impl.data.local_models.map.MapLandmark
import com.inno.impl.repositories.LandmarkRepository
import com.inno.impl.ui.map.MapUiState.MapState
import com.inno.impl.utils.iconTextStyle
import com.inno.impl.utils.toMapKitPoint
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor,
    private val repository: GeoRepository,
    private val context: Context,
    private val landmarkRepository: LandmarkRepository
) : ViewModel() {

    private var userMapObject: PlacemarkMapObject? = null

    private val tapListeners = mutableListOf<MapObjectTapListener>()

    @SuppressLint("StaticFieldLeak")
    val map = MapView(context)

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY())
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    init {
        subscribeToGeo()
        launch()
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
                    addAttractions(result.data.list)
                }

                is ResponseState.Error -> {
                    _uiStateFlow.update { it.copy(state = MapState.Error) }
                }
            }
        }
    }

    private fun addAttractions(list: List<MapLandmark>) {
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

                viewModelScope.launch {
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
            }
        }
    }

    private fun subscribeToGeo() {
        viewModelScope.launch {
            repository.geoInfoFlow().collectLatest { geoInfo ->
                updateUserLocation(geoPoint = geoInfo.currentPoint)
            }
        }
    }

    fun onMapAction(action: MapActions) {
        when (action) {
            MapActions.ModalDismissed -> {
                landmarkRepository.dismissLandmark()
            }

            is MapActions.OnPlaceMarkTapped -> {
                landmarkRepository.getLandmark(action.landmarkId)
            }

            MapActions.OnMapStarted -> onStart()

            MapActions.OnMapStopped -> onStop()

            MapActions.OnRelaunchMap -> launch()
        }
    }

    private fun updateUserLocation(geoPoint: GeoPoint) {
        val location = geoPoint.toMapKitPoint()
        if (userMapObject == null) {
            val mapObjectCollection = map.mapWindow.map.mapObjects

            userMapObject = mapObjectCollection.addPlacemark().apply {
                geometry = location
                setIcon(ImageProvider.fromResource(map.context, R.drawable.ic_dollar_pin))
            }
            moveToLocation(geoPoint)
        } else {
            userMapObject?.let {
                it.geometry = location
            }
        }
    }

    private fun moveToLocation(geoPoint: GeoPoint) {
        val location = geoPoint.toMapKitPoint()
        val zoomValue = 16.5f
        map.mapWindow.map.move(CameraPosition(location, zoomValue, 0.0f, 0.0f))
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