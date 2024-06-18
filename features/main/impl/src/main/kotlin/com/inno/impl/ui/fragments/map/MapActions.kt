package com.inno.impl.ui.fragments.map

sealed class MapActions {

    class OnPlaceMarkTapped(val landmarkId: String) : MapActions()

    object ModalDismissed : MapActions()

}