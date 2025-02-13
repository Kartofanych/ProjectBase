package com.search_filters.impl.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.search_filters.impl.ui.SearchUiState.SearchScreenState
import com.search_filters.impl.ui.composables.SearchField
import com.search_filters.impl.ui.composables.SearchResult
import com.search_filters.impl.ui.composables.ZeroSearch

@Composable
fun SearchScreen(uiState: SearchUiState, onAction: (SearchAction) -> Unit) {

    val textField = remember { mutableStateOf(TextFieldValue(text = uiState.searchString)) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {

            SearchField(uiState, onAction, textField)

            Box(modifier = Modifier.fillMaxSize()) {
                // android bug https://youtrack.jetbrains.com/issue/KT-48215
                androidx.compose.animation.AnimatedVisibility(
                    enter = slideInHorizontally { -it },
                    exit = slideOutHorizontally { -it },
                    visible = uiState.state is SearchScreenState.ZeroSearch
                ) {
                    if (uiState.state is SearchScreenState.ZeroSearch) {
                        ZeroSearch(
                            zeroState = uiState.state,
                            onAction = onAction,
                            textField = textField
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    enter = slideInHorizontally { it },
                    exit = slideOutHorizontally { it },
                    visible = uiState.state is SearchScreenState.SearchResults
                ) {
                    if (uiState.state is SearchScreenState.SearchResults) {
                        SearchResult(uiState.state, onAction)
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        textField.value = textField.value.copy(
            selection = TextRange(textField.value.text.length)
        )
        onDispose { }
    }
}
