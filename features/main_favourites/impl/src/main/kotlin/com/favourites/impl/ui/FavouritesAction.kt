package com.favourites.impl.ui

sealed interface FavouritesAction {

    object OnLogIn : FavouritesAction

    object OnLogOut : FavouritesAction

    class ChangeProfileModalVisibility(val isVisible: Boolean) : FavouritesAction

    object OnReload : FavouritesAction

    object OpenPromo : FavouritesAction

    class OnOpenAttraction(val attractionId: String) : FavouritesAction

    class OnLikeChanged(val index: Int) : FavouritesAction
}