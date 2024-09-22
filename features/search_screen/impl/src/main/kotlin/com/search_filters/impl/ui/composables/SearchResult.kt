package com.search_filters.impl.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.search_filters.impl.ui.SearchAction
import com.search_filters.impl.ui.SearchUiState.SearchResultsState
import com.search_filters.impl.ui.SearchUiState.SearchScreenState

@Composable
fun SearchResult(searchResults: SearchScreenState.SearchResults, onAction: (SearchAction) -> Unit) {
    when (searchResults.state) {

        SearchResultsState.Loading -> {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }

        is SearchResultsState.Error -> {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }

        is SearchResultsState.Results -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = searchResults.list,
                    key = {
                        it.id
                    }
                ) {
                    SearchActivity(entity = it, onAction = onAction)
                }
            }
        }
    }
}
