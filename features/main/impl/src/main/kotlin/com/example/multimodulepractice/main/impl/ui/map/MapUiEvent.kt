package com.example.multimodulepractice.main.impl.ui.map

sealed interface MapUiEvent {

    object OnFiltersOpen : MapUiEvent

    class OnAttractionOpen(val id: String) : MapUiEvent
}