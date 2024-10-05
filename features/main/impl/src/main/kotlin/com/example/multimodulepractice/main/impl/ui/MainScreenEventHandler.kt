package com.example.multimodulepractice.main.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.multimodulepractice.main.impl.data.models.MainTab
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreenEventHandler(
    uiEvent: Flow<MainUiEvent>,
    openSearch: () -> Unit,
    openMap: () -> Unit,
    openFavorites: () -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is MainUiEvent.OpenTab -> {
                    when (event.tab) {
                        MainTab.MAP -> openMap()
                        MainTab.LIST -> openSearch()
                        MainTab.FAVOURITES -> openFavorites()
                    }
                }
            }
        }
    }
}