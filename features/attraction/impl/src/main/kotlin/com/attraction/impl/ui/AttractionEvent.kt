package com.attraction.impl.ui

import com.example.multimodulepractice.common.data.models.network.ObjectType

sealed interface AttractionEvent {

    object OnBackPressed : AttractionEvent

    class OpenGuide(val attractionId: String) : AttractionEvent

    class OpenObject(val id: String, val type: ObjectType) : AttractionEvent
}