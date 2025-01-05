package com.example.travelling.main.impl.ui.map

import com.example.travelling.common.data.models.local.GeoPoint

sealed interface MapActions {

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions

    class OnCityTapped(val geoPoint: GeoPoint) : MapActions

    object OnFiltersOpen : MapActions

    object OnMyLocationClick : MapActions
}