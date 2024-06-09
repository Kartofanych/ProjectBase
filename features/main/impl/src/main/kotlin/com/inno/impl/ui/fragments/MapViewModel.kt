package com.inno.impl.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.common.di.AppContext
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.di.MainScope
import com.inno.impl.utils.innoCoordinates
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@MainScope
class MapViewModel @Inject constructor(
    @AppContext
    private val context: Context,
    private val mapView: MapView
) : ViewModel() {

    private val placemarkTapListener = MapObjectTapListener { _, point ->
        Toast.makeText(context, "Иннополис", Toast.LENGTH_SHORT).show()
        true
    }

    fun onStart() {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        //TODO будут с бэка приходить
        drawBoundary()
        setMarkerInStartLocation()
        moveToStartLocation()
    }

    private fun moveToStartLocation() {
        val startLocation = Point(55.752085, 48.744618)
        val zoomValue = 16.5f
        mapView.mapWindow.map.move(CameraPosition(startLocation, zoomValue, 0.0f, 0.0f))
    }


    private fun setMarkerInStartLocation() {
        val mapObjectCollection = mapView.mapWindow.map.mapObjects
        val obj = mapObjectCollection.addPlacemark().apply {
            geometry = Point(55.752085, 48.744618)
            setIcon(ImageProvider.fromResource(context, R.drawable.ic_dollar_pin))
        }
        obj.addTapListener(placemarkTapListener)
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

    fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

}