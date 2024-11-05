package com.start.impl.ui

sealed interface StartAction {
    object OnGuestLogin : StartAction

    object OnLogin : StartAction
}
