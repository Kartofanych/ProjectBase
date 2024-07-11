package com.example.multimodulepractice.main.impl.ui.list

sealed interface ListAction {

    class OpenAttraction(val id: String) : ListAction

}