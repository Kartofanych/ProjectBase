package com.inno.impl.ui.map

sealed interface MapUiEvent {

    class OnGuideClicked(val landmarkId: String) : MapUiEvent

}