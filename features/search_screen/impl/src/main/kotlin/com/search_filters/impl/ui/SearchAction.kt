package com.search_filters.impl.ui

import com.example.multimodulepractice.common.data.models.local.ActivityEntity

sealed interface SearchAction {
    object BackPressed : SearchAction

    object OpenFilters : SearchAction

    class ChangeSearchText(val search: String) : SearchAction

    class ActivityClicked(val entity: ActivityEntity) : SearchAction

    class OnScrollAction(val firstVisibleItem: Int) : SearchAction
}