package com.example.travelling.guide.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.guide.impl.R
import com.example.travelling.guide.impl.ui.GuideUiState
import kotlinx.coroutines.launch

@Composable
fun Footer(
    modifier: Modifier,
    uiState: GuideUiState.Content,
    currentScreen: Int,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
    ) {

        AnimatedVisibility(
            visible = currentScreen > 0,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clip(CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(45.dp)
                    .background(Color(0xFF737F89), CircleShape)
                    .clip(CircleShape)
                    .clickable(currentScreen > 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(currentScreen - 1)
                        }
                    },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_play),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 13.dp)
                        .size(18.dp)
                        .rotate(180f)
                )
            }
        }

        AnimatedVisibility(
            visible = currentScreen < uiState.topics.size - 1,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0xFF47D88D), CircleShape)
                    .clip(CircleShape)
                    .clickable(currentScreen < uiState.topics.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(currentScreen + 1)
                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_play),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 13.dp)
                        .size(18.dp)
                )
            }
        }
    }
}
