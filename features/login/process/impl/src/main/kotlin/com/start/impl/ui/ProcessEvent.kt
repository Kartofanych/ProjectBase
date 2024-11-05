package com.start.impl.ui

sealed interface ProcessEvent {

    object BackEvent : ProcessEvent

    object OpenMain : ProcessEvent
}
