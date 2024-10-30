package com.service.impl.ui

sealed interface ServiceAction {
    class Deeplink(val deeplink: String) : ServiceAction

    object OnBackPressed : ServiceAction

    object OnReload : ServiceAction
}