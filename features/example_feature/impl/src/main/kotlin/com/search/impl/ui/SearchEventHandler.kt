package com.search.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchEventHandler(
    uiEvent: Flow<SearchEvent>
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                else -> Unit
            }
        }
    }
}