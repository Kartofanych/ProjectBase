package com.filters.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.filters.impl.ui.composables.CategoriesSection
import com.filters.impl.ui.composables.DistanceSection
import com.filters.impl.ui.composables.HeaderSection

@Composable
fun FiltersScreen(
    distanceStateFlow: MutableFloatState,
    uiState: FiltersUiState,
    onAction: (FiltersAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .safeDrawingPadding()
        ) {
            HeaderSection(onAction)

            Spacer(modifier = Modifier.height(45.dp))

            CategoriesSection(uiState.categories, onAction)

            Spacer(modifier = Modifier.height(40.dp))

            DistanceSection(distanceStateFlow, onAction)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .height(45.dp)
                .width(225.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape,
                )
                .background(color = Color(0xFF74A3FF), shape = CircleShape)
                .clip(CircleShape)
                .clickable {
                    onAction(FiltersAction.OnClose(withUpdates = true))
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Применить",
                style = semiboldTextStyle.copy(fontSize = 18.sp, color = Color.White),
            )
        }
    }
}
