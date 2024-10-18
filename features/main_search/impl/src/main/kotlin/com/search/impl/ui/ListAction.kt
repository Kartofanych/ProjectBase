package com.search.impl.ui

sealed interface ListAction {

    class OpenAttraction(val id: String) : ListAction

    class OpenActivity(val id: String) : ListAction

    object OpenSearch : ListAction
}