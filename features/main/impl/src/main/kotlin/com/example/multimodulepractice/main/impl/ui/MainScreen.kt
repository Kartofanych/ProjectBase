package com.example.multimodulepractice.main.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.common.composables.TopShadow
import com.example.multimodulepractice.main.impl.data.models.MainTab
import com.example.multimodulepractice.main.impl.ui.compose_elements.Tab

@Composable
fun MainScreen(modifier: Modifier, uiState: MainUiState, onAction: (MainAction) -> Unit) {
    Box(modifier = modifier.height(62.dp)) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            for (tab in MainTab.entries) {
                Tab(
                    modifier = Modifier.weight(1f),
                    tabValue = tab,
                    uiState = uiState,
                    onAction = onAction
                )
            }
        }

        TopShadow(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(2.dp)
                .fillMaxWidth(),
            alpha = 0.05f
        )
    }
}