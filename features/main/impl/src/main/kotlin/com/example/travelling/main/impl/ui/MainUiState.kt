package com.example.travelling.main.impl.ui

import com.example.travelling.main.impl.data.models.MainTab

data class MainUiState(
    val currentTab: MainTab
) {
    companion object {
        val EMPTY = MainUiState(
            currentTab = MainTab.MAP
        )
    }
}
