package com.search_filters.impl.ui

sealed interface SearchFiltersEvent {

    object OnClose : SearchFiltersEvent
}