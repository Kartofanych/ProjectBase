package com.example.multimodulepractice.main.impl.ui.map

sealed interface MapActions {

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions

    object OnRelaunchMap : MapActions

    object OnFiltersOpen : MapActions

    object OnMyLocationClick : MapActions
}