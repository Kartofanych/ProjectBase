package com.example.multimodulepractice.main.impl.ui.map

import com.example.multimodulepractice.common.data.models.local.GeoPoint

sealed interface MapActions {

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions

    class OnCityTapped(val geoPoint: GeoPoint) : MapActions

    object OnFiltersOpen : MapActions

    object OnMyLocationClick : MapActions
}