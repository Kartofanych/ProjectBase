package com.search_filters.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.search_filters.impl.ui.composables.CitiesSection
import com.search_filters.impl.ui.composables.DistanceSection
import com.search_filters.impl.ui.composables.HeaderSection
import com.search_filters.impl.ui.composables.StarsSection

@Composable
fun SearchFiltersScreen(
    distanceStateFlow: MutableFloatState,
    starsStateFlow: MutableFloatState,
    uiState: SearchFiltersUiState,
    onAction: (SearchFiltersAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState()
                )
                .safeDrawingPadding()
        ) {
            HeaderSection(onAction)

            Spacer(modifier = Modifier.height(50.dp))

            DistanceSection(distanceStateFlow, onAction)

            Spacer(modifier = Modifier.height(40.dp))

            StarsSection(starsStateFlow, onAction)

            Spacer(modifier = Modifier.height(40.dp))

            CitiesSection(uiState, onAction)

            Spacer(modifier = Modifier.height(100.dp))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .height(45.dp)
                .width(225.dp)
                .background(color = Color(0xFF74A3FF), shape = CircleShape)
                .clip(CircleShape)
                .touchAction {
                    onAction(SearchFiltersAction.OnClose(withUpdates = true))
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Применить",
                style = semiboldTextStyle.copy(fontSize = 18.sp, color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun SearchFiltersPreview() {
    SearchFiltersScreen(
        remember {
            mutableFloatStateOf(400f)
        },
        remember {
            mutableFloatStateOf(400f)
        },
        uiState = SearchFiltersUiState(listOf())
    ) {

    }
}

