package com.filters.impl.ui

sealed interface FiltersUiEvent {
    object OnClose : FiltersUiEvent
}