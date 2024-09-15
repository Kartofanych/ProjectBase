package com.attraction.impl.ui

sealed interface AttractionAction {

    object RecallAttraction : AttractionAction

    object OpenGuide : AttractionAction

    class OpenService(val serviceId: String) : AttractionAction
}