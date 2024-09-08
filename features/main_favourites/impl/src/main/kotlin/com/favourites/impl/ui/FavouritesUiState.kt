package com.favourites.impl.ui

import com.main.common.data.local.Attraction

data class FavouritesUiState(
    val recommendedList: List<Attraction> = emptyList(),
    val mode: ProfileMode = ProfileMode.GuestProfile
) {

    sealed interface ProfileMode {

        object GuestProfile : ProfileMode

        data class UserProfile(
            val name: String,
            val email: String,
            val image: String
        ) : ProfileMode
    }
}