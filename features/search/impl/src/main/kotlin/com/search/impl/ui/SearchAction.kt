package com.search.impl.ui

import com.example.multimodulepractice.common.data.models.local.ActivityEntity

sealed interface SearchAction {
    object BackPressed : SearchAction

    class ChangeSearchText(val search: String) : SearchAction

    class ActivityClicked(val entity: ActivityEntity) : SearchAction
}