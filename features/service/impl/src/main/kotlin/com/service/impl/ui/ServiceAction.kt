package com.service.impl.ui

sealed interface ServiceAction {

    object OnBackPressed : ServiceAction

    object OnReload : ServiceAction

    object OnCall : ServiceAction
}