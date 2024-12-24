package com.favourites.impl.ui

import com.favourites.impl.data.models.local.FavoriteAttraction

data class FavouritesUiState(
    val state: FavouritesState,
    val isModalVisible: Boolean
) {

    sealed interface FavouritesState {
        object Loading : FavouritesState

        object Error : FavouritesState

        object Unauthorized : FavouritesState

        data class Authorized(val items: List<FavoriteAttraction>, val user: UserProfile) :
            FavouritesState
    }

    data class UserProfile(
        val name: String,
        val image: String,
        val promoCount: Int,
    )

    companion object {
        fun empty(): FavouritesUiState {
            return FavouritesUiState(
                state = FavouritesState.Loading,
                isModalVisible = false,
            )
        }
    }
}