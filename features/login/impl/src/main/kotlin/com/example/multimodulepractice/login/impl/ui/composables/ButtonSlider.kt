package com.example.multimodulepractice.login.impl.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.SwipeableState
import androidx.wear.compose.material.swipeable
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.login.impl.R
import com.example.multimodulepractice.login.impl.ui.models.ConfirmationState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
private fun DraggableControl(
    modifier: Modifier
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
        Image(
            painter = painterResource(id = R.drawable.icon_yandex),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun YandexLoginButton(
    swipeableState: SwipeableState<ConfirmationState>,
    progress: State<Float>,
) {
    val scope = rememberCoroutineScope()
    val sizePx = with(LocalDensity.current) {
        190.dp.toPx()
    }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)

    Box(
        modifier = Modifier
            .width(240.dp)
            .swipeable(
                enabled = progress.value != 1f,
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.Black, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                if (swipeableState.currentValue == ConfirmationState.DEFAULT) {
                    scope.launch {
                        swipeableState.animateTo(ConfirmationState.CONFIRMED)
                    }
                }
            }
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
                .size(50.dp)
                .rotate(maxOf(0f, minOf(360f, progress.value * 360)))
        )
    }
}