package com.example.multimodulepractice.guide.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.guide.impl.ui.composables.ContentImage
import com.example.multimodulepractice.guide.impl.ui.composables.Footer
import com.example.multimodulepractice.guide.impl.ui.composables.Header
import com.example.multimodulepractice.guide.impl.ui.composables.Info

@Composable
fun GuideScreen(uiState: GuideUiState, onAction: (GuideAction) -> Unit) {

    if (uiState is GuideUiState.Content) {
        val pageCounter = uiState.topics.size
        val pagerState = rememberPagerState(pageCount = { pageCounter })

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
                    .background(Color.White)
            ) { page ->
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        item {
                            ContentImage(uiState.topics[page].image)
                        }
                        itemsIndexed(
                            items = uiState.topics[page].texts,
                            key = { index, _ -> index }
                        ) { index, item ->
                            Info(
                                item,
                                isLast = index == uiState.topics[page].texts.size - 1,
                                isFirst = index == 0
                            )
                        }
                    }
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
        DefaultLoading(modifier = Modifier.fillMaxSize())
    }
}
