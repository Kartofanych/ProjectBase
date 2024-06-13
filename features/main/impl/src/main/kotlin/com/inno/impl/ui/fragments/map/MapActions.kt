package com.inno.impl.ui.fragments.map

sealed class MapActions {

    object OnPlaceMarkTapped : MapActions()

    object ModalDismissed : MapActions()

}