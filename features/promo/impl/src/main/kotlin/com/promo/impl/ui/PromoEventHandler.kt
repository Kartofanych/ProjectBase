package com.promo.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun PromoEventHandler(
    uiEvent: Flow<PromoEvent>
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                else -> Unit
            }
        }
    }
}