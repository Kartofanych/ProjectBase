package com.item.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.multimodulepractice.common.data.models.network.ObjectType
import kotlinx.coroutines.flow.Flow

@Composable
fun PromoItemEventHandler(
    uiEvent: Flow<PromoItemEvent>,
    onBack: () -> Boolean,
    navigateToService: (String) -> Unit,
    navigateToAttraction: (String) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                PromoItemEvent.OnBack -> onBack()
                is PromoItemEvent.OnOpenInfo -> {
                    when (event.type) {
                        ObjectType.ATTRACTION -> navigateToAttraction(event.id)
                        ObjectType.SERVICE -> navigateToService(event.id)
                    }
                }
            }
        }
    }
}
