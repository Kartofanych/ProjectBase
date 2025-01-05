package com.attraction.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.attraction.impl.ui.AttractionAction
import com.attraction.impl.ui.AttractionUiState
import com.attraction.impl.ui.ReviewModalState
import com.example.travelling.common.composables.DefaultSeparator

@Composable
fun AttractionContent(
    uiState: AttractionUiState.Content,
    reviewModalState: ReviewModalState,
    onAction: (AttractionAction) -> Unit
) {
    val attraction = uiState.landmark

    Scaffold(
        content = { scaffold ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
            ) {

                ImageSlider(scaffold.calculateTopPadding(), attraction, onAction)

                Spacer(modifier = Modifier.height(16.dp))

                InformationBlock(
                    attraction = attraction,
                    onAction = onAction
                )

                DefaultSeparator()

                Spacer(modifier = Modifier.height(24.dp))

                if (attraction.closeObjectsBlock.isNotEmpty()) {
                    for (block in attraction.closeObjectsBlock) {
                        ServicesBlock(block, onAction)

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    DefaultSeparator()
                }

                Spacer(modifier = Modifier.height(24.dp))

                ReviewBlock(attraction, onAction)

                DefaultSeparator()
            }

            AnimatedVisibility(
                visible = attraction.schedule.isVisible,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it },
            ) {
                ScheduleScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = scaffold.calculateTopPadding() + 10.dp),
                    onAction = onAction,
                    schedule = attraction.schedule
                )
            }

            ReviewModal(reviewModalState, onAction, scaffold.calculateBottomPadding())
        }
    )
}
