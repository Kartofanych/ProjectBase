package com.example.travelling.geo.repository

import com.example.travelling.common.data.models.local.GeoPoint
import com.example.travelling.common.utils.calculateDistance
import javax.inject.Inject

//TODO create api interface
class PreferredRouteTypeInteractor @Inject constructor() {
    fun bestOption(userGeoPoint: GeoPoint, targetGeoPoint: GeoPoint): PreferredRouteType {
        return when {
            calculateDistance(userGeoPoint, targetGeoPoint) < 5 -> PreferredRouteType.WALKER
            else -> PreferredRouteType.AUTO
        }
    }
}
