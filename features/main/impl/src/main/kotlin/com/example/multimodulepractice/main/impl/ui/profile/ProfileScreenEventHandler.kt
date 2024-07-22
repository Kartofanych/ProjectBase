package com.example.multimodulepractice.main.impl.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileScreenEventHandler(
    navigateToLogin: () -> Unit,
    uiEvent: Flow<ProfileUiEvent>
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                ProfileUiEvent.LogOut -> navigateToLogin()
            }
        }
    }

}