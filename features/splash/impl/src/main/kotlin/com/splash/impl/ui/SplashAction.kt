package com.splash.impl.ui

sealed interface SplashAction {
    object Update: SplashAction
}