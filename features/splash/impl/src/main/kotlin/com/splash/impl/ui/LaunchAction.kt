package com.splash.impl.ui

sealed interface LaunchAction {

    object Update : LaunchAction

    object Reload : LaunchAction
}