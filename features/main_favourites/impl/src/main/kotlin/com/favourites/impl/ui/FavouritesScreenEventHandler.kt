package com.favourites.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileScreenEventHandler(
    navigateToLogin: () -> Unit,
    uiEvent: Flow<FavouritesUiEvent>
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                FavouritesUiEvent.LogOut -> navigateToLogin()
            }
        }
    }

}