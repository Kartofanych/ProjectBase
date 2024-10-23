package com.favourites.impl.ui

import com.favourites.impl.data.models.local.FavoriteAttraction

sealed interface FavouritesUiState {

    object Loading : FavouritesUiState

    object Error : FavouritesUiState

    object Unauthorized : FavouritesUiState

    data class Authorized(val items: List<FavoriteAttraction>) : FavouritesUiState

    sealed interface ProfileMode {

        object GuestProfile : ProfileMode

        data class UserProfile(
            val name: String,
            val email: String,
            val image: String
        ) : ProfileMode
    }
}