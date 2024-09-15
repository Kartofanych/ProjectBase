package com.attraction.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(modifier: Modifier, pagerState: PagerState) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color.White else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
                    .size(43.dp, 2.5.dp)
            )
        }
    }
}