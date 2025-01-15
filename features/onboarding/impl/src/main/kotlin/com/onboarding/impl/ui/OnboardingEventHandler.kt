package com.onboarding.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun OnboardingEventHandler(
    uiEvent: Flow<OnboardingEvent>,
    openLogin: () -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                OnboardingEvent.OpenLogin -> openLogin()
            }
        }
    }
}