package com.attraction.impl.ui

sealed interface AttractionEvent {

    object OnBackPressed : AttractionEvent

    class OpenGuide(val attractionId: String) : AttractionEvent

    class OpenService(val serviceId: String) : AttractionEvent
}