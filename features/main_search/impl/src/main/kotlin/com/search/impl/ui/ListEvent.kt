package com.search.impl.ui

sealed interface ListEvent {
    class OpenService(val id: String) : ListEvent

    class OpenAttraction(val id: String) : ListEvent

    object OpenSearch : ListEvent
}