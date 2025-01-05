package com.example.travelling.guide.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun GuideScreenEventHandler(uiEvent: Flow<GuideUiEvent>, navigateBack: () -> Boolean) {

    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is GuideUiEvent.OnBackClicked -> navigateBack()
            }
        }
    }

}
