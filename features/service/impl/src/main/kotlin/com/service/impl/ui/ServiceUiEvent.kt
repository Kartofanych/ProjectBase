package com.service.impl.ui

sealed interface ServiceUiEvent {

    object OnBackPressed : ServiceUiEvent
}