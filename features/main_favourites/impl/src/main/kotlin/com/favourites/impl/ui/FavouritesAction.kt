package com.favourites.impl.ui

sealed interface FavouritesAction {

    object OnLogOut : FavouritesAction

    class OnOpenAttraction(val attractionId: String) : FavouritesAction

}