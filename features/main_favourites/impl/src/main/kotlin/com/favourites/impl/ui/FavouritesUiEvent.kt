package com.favourites.impl.ui

sealed interface FavouritesUiEvent {

    object LogOut : FavouritesUiEvent

    object OpenPromo : FavouritesUiEvent

    class OpenAttraction(val id: String) : FavouritesUiEvent
}