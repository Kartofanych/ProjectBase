package com.inno.impl.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.widget.Toast
import androidx.core.content.ContextCompat
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
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
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

    init {
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
        val marker = createBitmapFromVector(R.drawable.ic_pin_black_svg)
        val mapObjectCollection: MapObjectCollection = mapView.mapWindow.map.mapObjects
        //TODO текущую локацию
        val placemarkMapObject: PlacemarkMapObject = mapObjectCollection.addPlacemark(
            Point(55.752085, 48.744618),
            ImageProvider.fromBitmap(marker)
        )
        placemarkMapObject.opacity = 0.5f
        placemarkMapObject.addTapListener { _, _ ->
            Toast.makeText(context, "Иннополис", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun createBitmapFromVector(art: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, art) ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun drawBoundary() {
        val mapObjects: MapObjectCollection = mapView.mapWindow.map.mapObjects.addCollection()
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