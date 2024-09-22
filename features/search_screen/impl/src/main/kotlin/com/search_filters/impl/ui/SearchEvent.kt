package com.search_filters.impl.ui

sealed interface SearchEvent {
    object OnBackPressed : SearchEvent

    class OnOpenAttraction(val id: String) : SearchEvent

    class OnOpenService(val id: String) : SearchEvent

    object OnOpenFilters : SearchEvent
}