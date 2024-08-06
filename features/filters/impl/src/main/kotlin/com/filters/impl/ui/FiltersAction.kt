package com.filters.impl.ui

import com.filters.api.data.models.FiltersCategory

sealed interface FiltersAction {

    object OnZeroFilters : FiltersAction

    class OnClose(val withUpdates: Boolean) : FiltersAction

    class OnDistanceChanged(val value: Float) : FiltersAction

    class OnCategoryClicked(val category: FiltersCategory) : FiltersAction
}