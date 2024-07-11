package com.example.multimodulepractice.geo

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.models.local.GeoPoint
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

    @SuppressLint("MissingPermission")
    fun start() {
        if (ContextCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.let(::updateLocation)

        val listener = LocationListener(::updateLocation)

        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            MIN_UPDATE_TIME,
            MIN_UPDATE_DISTANCE,
            listener
        )

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