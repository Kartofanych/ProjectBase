package com.example.multimodulepractice.main.impl.ui

import com.example.multimodulepractice.main.impl.data.models.MainTab

sealed interface MainAction {

    class OpenTab(val tab: MainTab) : MainAction
}