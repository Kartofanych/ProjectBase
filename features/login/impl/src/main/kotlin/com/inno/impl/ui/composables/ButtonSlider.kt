package com.inno.impl.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.login.R
import com.inno.impl.ui.models.ConfirmationState
import kotlin.math.roundToInt

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                CircleShape,
                clip = false
            )
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed = remember {
            derivedStateOf { progress >= 0.8f }
        }

        Image(
            painter = painterResource(id = R.drawable.icon_yandex),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun YandexLoginButton(
    modifier: Modifier = Modifier
) {
    val width = 240.dp
    val dragSize = 50.dp
    val swipeableState = rememberSwipeableState(initialValue = ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) {
        (width - dragSize).toPx()
    }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)
    val progress = remember {
        derivedStateOf {
            if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.Black, RoundedCornerShape(dragSize))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Yandex ID",
                style = semiboldTextStyle.copy(color = Color.White),
                modifier = Modifier.padding(start = 25.dp)
            )
        }

        DraggableControl(
            modifier = Modifier
                .offset {
                    IntOffset(
                        maxOf(
                            0,
                            minOf(swipeableState.offset.value.roundToInt(), sizePx.toInt())
                        ), 0
                    )
                }
                .size(dragSize),
            progress = progress.value
        )
    }
}