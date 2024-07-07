package com.inno.impl.ui.map

sealed interface MapActions {

    object OnMapStarted : MapActions

    object OnMapStopped : MapActions

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions

    object ModalDismissed : MapActions

    object OnRelaunchMap : MapActions

}