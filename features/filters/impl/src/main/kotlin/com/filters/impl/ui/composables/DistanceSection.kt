package com.filters.impl.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.common.utils.dpToPx
import com.filters.impl.ui.FiltersAction
import kotlin.math.abs
import kotlin.math.min

@Composable
fun DistanceSection(distanceFloatState: MutableFloatState, onAction: (FiltersAction) -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 300.dp)
            .padding(horizontal = 27.dp)
    ) {
        Text(
            text = "Дистанция",
            style = semiboldTextStyle.copy(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SliderItem(distanceFloatState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SliderItem(distanceFloatState: MutableFloatState, onAction: (FiltersAction) -> Unit) {
    val items = listOf("500м", "1 км", "2 км", "5 км", "Город")
    val multiplier = 100f

    val interactionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    val drawPadding = context.dpToPx(10f)
    val textSize = context.dpToPx(10f)
    val lineHeightPx = context.dpToPx(3f)
    val canvasHeight = 50.dp
    val textPaint = android.graphics.Paint().apply {
        color = if (isSystemInDarkTheme()) 0xffffffff.toInt() else 0xffff47586B.toInt()
        textAlign = android.graphics.Paint.Align.CENTER
        this.textSize = textSize
    }
    Box(contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .height(canvasHeight)
                .fillMaxWidth()
                .padding(
                    top = canvasHeight
                        .div(2)
                        .minus(10.dp.div(2))
                )
        ) {
            val yStart = 0f
            val distance = (size.width.minus(2 * drawPadding)).div(items.size.minus(1))
            items.forEachIndexed { index, date ->
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(x = drawPadding + index.times(distance), y = yStart),
                    end = Offset(x = drawPadding + index.times(distance), y = lineHeightPx)
                )
                this.drawContext.canvas.nativeCanvas.drawText(
                    date,
                    drawPadding + index.times(distance),
                    size.height,
                    textPaint
                )
            }
        }
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = distanceFloatState.floatValue,
            interactionSource = interactionSource,
            valueRange = 0f..(items.size - 1) * multiplier,
            steps = (items.size - 2) * multiplier.toInt(),
            colors = customSliderColors(),
            onValueChange = {
                distanceFloatState.floatValue = it
            },
            onValueChangeFinished = {
                val sliderValue = distanceFloatState.floatValue
                val step = sliderValue.toInt() / multiplier.toInt()
                val g1 = abs(sliderValue - step * multiplier)
                val g2 = abs(sliderValue - (step - 1) * multiplier)
                val g3 = abs(sliderValue - (step + 1) * multiplier)
                val closest = min(g1, min(g2, g3))
                when (closest) {
                    g1 -> onAction(FiltersAction.OnDistanceChanged(step * multiplier))
                    g2 -> onAction(FiltersAction.OnDistanceChanged((step - 1) * multiplier))
                    g3 -> onAction(FiltersAction.OnDistanceChanged((step + 1) * multiplier))
                }
            },
            thumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.size(20.dp),
                    interactionSource = interactionSource,
                    colors = customSliderColors()
                )
            },
            track = {
                SliderDefaults.Track(
                    sliderState = it,
                    colors = customSliderColors(),
                    modifier = Modifier.height(3.dp),
                    drawStopIndicator = null
                )
            }
        )
    }
}

@Composable
private fun customSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent,
    thumbColor = Color(0xFF74A3FF),
    activeTrackColor = Color(0xFF74A3FF),
    inactiveTrackColor = Color(0xFFEDF1FD)
)