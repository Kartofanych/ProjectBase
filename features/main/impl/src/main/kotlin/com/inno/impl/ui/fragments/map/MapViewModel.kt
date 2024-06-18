package com.inno.impl.ui.fragments.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.models.local.GeoPoint
import com.example.common.models.local.ResponseState
import com.example.common.utils.runWithMinTime
import com.example.multimodulepractice.main.impl.R
import com.inno.geo.repository.GeoRepository
import com.inno.impl.data.interactors.MapInteractor
import com.inno.impl.data.local.models.MapLandmark
import com.inno.impl.ui.fragments.map.MapUiState.MapState
import com.inno.impl.utils.toMapKitPoint
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraPosition
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
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor,
    private val repository: GeoRepository,
    context: Context
) : ViewModel() {

    private var userMapObject: PlacemarkMapObject? = null

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

    fun launch() {
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

                addTapListener { _, _ ->
                    onMapAction(MapActions.OnPlaceMarkTapped(landmark.id))
                    true
                }

                viewModelScope.launch(Dispatchers.IO) {
                    val image = try {
                        val url = URL(landmark.icon)
                        ImageProvider.fromBitmap(
                            BitmapFactory.decodeStream(
                                url.openConnection().getInputStream()
                            )
                        )
                    } catch (e: Exception) {
                        ImageProvider.fromResource(map.context, R.drawable.ic_dollar_pin)
                    }
                    withContext(Dispatchers.Main) {
                        setIcon(image)
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
                _uiStateFlow.update {
                    it.copy(currentLandmarkId = null)
                }
            }

            is MapActions.OnPlaceMarkTapped -> {
                _uiStateFlow.update {
                    it.copy(currentLandmarkId = action.landmarkId)
                }
            }
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

    fun onStart() {
        map.onStart()
    }

    fun onStop() {
        map.onStop()
    }

}