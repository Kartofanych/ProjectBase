package com.example.multimodulepractice.geo

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.data.models.local.GeoPoint
import com.example.multimodulepractice.geo.repository.GeoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScope
class GeoManager @Inject constructor(
    @AppContext
    private val context: Context,
    private val geoRepository: GeoRepository,
    private val scope: CoroutineScope
) {

    private val locationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private var listener: LocationListener? = null

    @SuppressLint("MissingPermission")
    fun start() {
        if (listener != null) return

        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?.let(::updateLocation)

        listener = LocationListener(::updateLocation).also {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_UPDATE_TIME,
                MIN_UPDATE_DISTANCE,
                it
            )
        }
    }

    private fun updateLocation(point: Location) {
        scope.launch {
            geoRepository.updateGeoPoint(point.toGeoPoint())
        }
    }

    private companion object {
        const val MIN_UPDATE_DISTANCE: Float = 100f
        const val MIN_UPDATE_TIME = (1000).toLong()
    }
}

private fun Location.toGeoPoint(): GeoPoint {
    return GeoPoint(
        lat = latitude.toFloat(),
        lon = longitude.toFloat()
    )
}