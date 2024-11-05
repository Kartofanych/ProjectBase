package com.start.impl.ui

sealed interface StartEvent {

    object OnGuestLogin : StartEvent

    object OnLogin : StartEvent
}
