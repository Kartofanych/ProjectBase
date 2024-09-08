package com.search.impl.ui

sealed interface ListAction {

    class OpenAttraction(val id: String) : ListAction
}