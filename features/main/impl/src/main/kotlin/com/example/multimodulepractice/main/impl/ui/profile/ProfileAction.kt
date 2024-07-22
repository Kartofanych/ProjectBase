package com.example.multimodulepractice.main.impl.ui.profile

sealed interface ProfileAction {

    object OnLogOut : ProfileAction


    class OnOpenAttraction(val attractionId: String) : ProfileAction

}