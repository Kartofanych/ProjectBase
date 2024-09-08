package com.favourites.impl.ui

sealed interface FavouritesUiEvent {
    object LogOut : FavouritesUiEvent
}