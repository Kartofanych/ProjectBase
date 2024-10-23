package com.favourites.impl.ui

sealed interface FavouritesAction {

    object OnLogIn : FavouritesAction

    object OnLogOut : FavouritesAction

    object OnOpenProfile : FavouritesAction

    object OnReload : FavouritesAction

    class OnOpenAttraction(val attractionId: String) : FavouritesAction

    class OnLikeChanged(val index: Int) : FavouritesAction
}