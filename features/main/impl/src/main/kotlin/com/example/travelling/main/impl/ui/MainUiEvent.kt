package com.example.travelling.main.impl.ui

import com.example.travelling.main.impl.data.models.MainTab

sealed interface MainUiEvent {

    class OpenTab(val tab: MainTab) : MainUiEvent
}