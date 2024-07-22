package com.example.multimodulepractice.main.impl.ui.profile

import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction

data class ProfileUiState(
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