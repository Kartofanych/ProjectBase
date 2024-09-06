package com.splash.impl.ui

sealed interface SplashEvent {

    object Start: SplashEvent

    object Update: SplashEvent

    object Close: SplashEvent

    class Error(type: ErrorType): SplashEvent
}

enum class ErrorType {
    INTERNET_ERROR,
    APP_VERSION_ERROR,
}
