package com.service.impl.ui

sealed interface ServiceUiEvent {
    class OpenReviews(val id: String) : ServiceUiEvent

    object OnBackPressed : ServiceUiEvent
}