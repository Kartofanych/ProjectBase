package com.search_filters.impl.ui

sealed interface SearchFiltersAction {

    object OnZeroFilters : SearchFiltersAction

    class OnClose(val withUpdates: Boolean) : SearchFiltersAction

    class OnDistanceChanged(val value: Float) : SearchFiltersAction

    class OnStarChanged(val value: Float) : SearchFiltersAction

    class CityStateChanged(val index: Int) : SearchFiltersAction
}