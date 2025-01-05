package com.example.travelling.main.impl.ui

import com.example.travelling.main.impl.data.models.MainTab

sealed interface MainAction {

    class OpenTab(val tab: MainTab) : MainAction
}