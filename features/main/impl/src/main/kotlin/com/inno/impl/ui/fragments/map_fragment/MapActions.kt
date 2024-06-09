package com.inno.impl.ui.fragments.map_fragment

sealed class MapActions {

    object OnPlaceMarkTapped : MapActions()

    object ModalDismissed : MapActions()

}