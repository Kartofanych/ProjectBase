package com.example.multimodulepractice.main.impl.ui.profile

sealed interface ProfileUiEvent {
    object LogOut : ProfileUiEvent
}