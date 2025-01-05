package com.attraction.impl.ui

import com.example.travelling.common.data.models.network.ObjectType

sealed interface AttractionEvent {

    object OnBackPressed : AttractionEvent

    class OpenGuide(val attractionId: String) : AttractionEvent

    class OpenObject(val id: String, val type: ObjectType) : AttractionEvent

    class OpenReviews(val id: String) : AttractionEvent
}