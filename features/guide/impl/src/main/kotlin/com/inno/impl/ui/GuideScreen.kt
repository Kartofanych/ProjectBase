package com.inno.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.inno.impl.ui.composables.ContentImage
import com.inno.impl.ui.composables.Footer
import com.inno.impl.ui.composables.Header
import com.inno.impl.ui.composables.Info

@Composable
fun GuideScreen(uiState: GuideUiState, onAction: (GuideAction) -> Unit) {

    if (uiState is GuideUiState.Content) {
        val pageCounter = uiState.topics.size
        val pagerState = rememberPagerState(pageCount = { pageCounter })
        val imageHeight = remember { mutableFloatStateOf(16f) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) { page ->

                val scrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ContentImage(uiState.topics[page].image, imageHeight)

                    Info(uiState.topics[page].text, imageHeight)
                }
            }

            Header(
                modifier = Modifier.safeDrawingPadding(),
                onAction = onAction,
                uiState = uiState,
                currentScreen = pagerState.currentPage
            )

            Footer(
                modifier = Modifier.align(Alignment.BottomCenter),
                uiState = uiState,
                currentScreen = pagerState.currentPage,
                pagerState = pagerState
            )
        }
    }

    AnimatedVisibility(
        visible = uiState == GuideUiState.Loading,
        exit = fadeOut()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CircularProgressIndicator(
                color = Color(0xFF47D88D),
                strokeWidth = 4.dp,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
