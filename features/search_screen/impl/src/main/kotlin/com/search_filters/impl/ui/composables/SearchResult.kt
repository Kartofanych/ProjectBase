package com.search_filters.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.search_filters.impl.ui.SearchAction
import com.search_filters.impl.ui.SearchUiState.SearchResultsState
import com.search_filters.impl.ui.SearchUiState.SearchScreenState

@Composable
fun SearchResult(searchResults: SearchScreenState.SearchResults, onAction: (SearchAction) -> Unit) {
    val state = searchResults.state
    val scrollState = rememberLazyListState()
    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }.collect {
            onAction(SearchAction.OnScrollAction(it))
        }
    }

    AnimatedVisibility(state is SearchResultsState.Results || state is SearchResultsState.Loading) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = scrollState,
        ) {
            items(
                items = searchResults.list,
                key = {
                    it.id
                }
            ) {
                SearchActivity(entity = it, onAction = onAction)
            }

            if (state == SearchResultsState.Results && searchResults.list.isEmpty()) {
                item {
                    Text(
                        text = "Ничего не найдено",
                        style = mediumTextStyle.copy(fontSize = 14.sp, color = Color(0xFF959595)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (state == SearchResultsState.Loading && searchResults.list.isNotEmpty()) {
                item {
                    DefaultLoading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        if (state == SearchResultsState.Loading && searchResults.list.isEmpty()) {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }
    }
}
