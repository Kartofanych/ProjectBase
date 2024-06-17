package com.inno.impl.ui.fragments.map

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.models.GeoPoint
import com.example.common.theme.MultimodulePracticeTheme
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.utils.innoCoordinates
import com.inno.impl.utils.toMapKitPoint
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment() {

    //TODO create map in module?
    private lateinit var mapView: MapView
    private var userMapObject: PlacemarkMapObject? = null
    private val viewModel: MapViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mapView = MapView(requireActivity())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateFlow.collectLatest {
                updateUserLocation(it.location)
            }
        }

        return ComposeView(requireActivity()).apply {
            setContent {
                MultimodulePracticeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val sheetState = rememberModalBottomSheetState()
                        val uiState = viewModel.uiStateFlow.collectAsState().value

                        MapEventHandler(uiEvent = viewModel.uiEvent)

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            AndroidView(
                                factory = {
                                    mapView
                                },
                                modifier = Modifier.fillMaxSize()
                            )

                            if (uiState.currentLandmarkId != null) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        viewModel.onMapAction(MapActions.ModalDismissed)
                                    },
                                    sheetState = sheetState,
                                    modifier = Modifier.heightIn(min = 300.dp)
                                ) {}
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        //TODO будут с бэка приходить
        drawBoundary()
    }

    private val placemarkTapListener = MapObjectTapListener { _, point ->
        viewModel.onMapAction(MapActions.OnPlaceMarkTapped)
        true
    }

    private fun updateUserLocation(geoPoint: GeoPoint) {
        val location = geoPoint.toMapKitPoint()
        if (userMapObject == null) {
            val mapObjectCollection = mapView.mapWindow.map.mapObjects
            userMapObject = mapObjectCollection.addPlacemark().apply {
                geometry = viewModel.uiStateFlow.value.location.toMapKitPoint()
                setIcon(ImageProvider.fromResource(context, R.drawable.ic_dollar_pin))
                addTapListener(placemarkTapListener)
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
        mapView.mapWindow.map.move(CameraPosition(location, zoomValue, 0.0f, 0.0f))
    }

    private fun drawBoundary() {
        val mapObjects = mapView.mapWindow.map.mapObjects.addCollection()
        val outerBoundaryCoordinates = listOf(
            Point(89.0, -170.0),
            Point(89.0, 170.0),
            Point(-89.0, 170.0),
            Point(-89.0, -170.0)
        )

        val polygon = Polygon(
            LinearRing(outerBoundaryCoordinates),
            listOf(LinearRing(innoCoordinates))
        )
        val polygonMapObject: PolygonMapObject = mapObjects.addPolygon(polygon)

        val fillColor = Color.argb(50, 255, 0, 0)
        polygonMapObject.fillColor = fillColor

        val strokeColor = Color.argb(100, 255, 0, 0)
        polygonMapObject.strokeColor = strokeColor

        polygonMapObject.strokeWidth = 2.0f
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

}