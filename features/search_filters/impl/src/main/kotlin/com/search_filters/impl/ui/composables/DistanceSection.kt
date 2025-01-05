package com.search_filters.impl.ui.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.search_filters.impl.ui.SearchFiltersAction
import kotlin.math.abs
import kotlin.math.min

@Composable
fun DistanceSection(
    distanceFloatState: MutableFloatState,
    onAction: (SearchFiltersAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 27.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Дистанция",
            style = semiboldTextStyle.copy(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SliderItem(distanceFloatState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SliderItem(
    distanceFloatState: MutableFloatState,
    onAction: (SearchFiltersAction) -> Unit
) {
    val items = listOf("500 м", "1 км", "2 км", "5 км", "Везде")
    val multiplier = 100f

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            value = distanceFloatState.floatValue,
            interactionSource = interactionSource,
            valueRange = 0f..(items.size - 1) * multiplier,
            steps = (items.size - 2) * multiplier.toInt(),
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
                    g1 -> onAction(SearchFiltersAction.OnDistanceChanged(step * multiplier))
                    g2 -> onAction(SearchFiltersAction.OnDistanceChanged((step - 1) * multiplier))
                    g3 -> onAction(SearchFiltersAction.OnDistanceChanged((step + 1) * multiplier))
                }
            },
            thumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.size(20.dp),
                    interactionSource = interactionSource,
                    colors = sliderCustomColors()
                )
            },
            track = {
                SliderDefaults.Track(
                    sliderState = it,
                    modifier = Modifier.height(3.dp),
                    drawStopIndicator = null,
                    colors = sliderCustomColors()
                )
            }
        )
        Row(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 5) {
                Text(
                    text = items[i],
                    style = mediumTextStyle.copy(
                        fontSize = 14.sp,
                        color = if (i.toFloat() * multiplier == distanceFloatState.floatValue) Color.Black else Color(
                            0xFF959595
                        )
                    )
                )
            }
        }
    }
}

@Composable
internal fun sliderCustomColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color(0xFF74A3FF),
    inactiveTickColor = Color.Transparent,
    thumbColor = Color(0xFF74A3FF),
    activeTrackColor = Color(0xFF74A3FF),
    inactiveTrackColor = Color(0xFFEDF1FD)
)