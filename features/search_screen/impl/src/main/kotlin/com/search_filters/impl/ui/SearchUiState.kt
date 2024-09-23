package com.search_filters.impl.ui

import com.example.multimodulepractice.common.data.models.local.ActivityEntity

data class SearchUiState(
    val searchString: String,
    val state: SearchScreenState
) {

    sealed interface SearchScreenState {
        data class ZeroSearch(val popularCities: List<String>) : SearchScreenState

        data class SearchResults(
            val list: List<ActivityEntity>,
            val state: SearchResultsState
        ) : SearchScreenState
    }

    sealed interface SearchResultsState {
        object Loading : SearchResultsState

        object Error : SearchResultsState

        object Results : SearchResultsState
    }

    companion object {
        fun empty(cities: List<String>) = SearchUiState(
            searchString = "",
            state = SearchScreenState.ZeroSearch(cities)
        )
    }
}