package com.inno.impl.ui.map

sealed class MapActions {

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions()

    object ModalDismissed : MapActions()

}