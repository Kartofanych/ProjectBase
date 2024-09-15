package com.search.impl.ui

sealed interface ListEvent {
    class OpenAttraction(val id: String) : ListEvent

    object OpenSearch : ListEvent
}