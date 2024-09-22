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
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.search_filters.impl.ui.SearchFiltersAction
import kotlin.math.abs
import kotlin.math.min

@Composable
fun StarsSection(
    starsFloatState: MutableFloatState,
    onAction: (SearchFiltersAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 27.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Рейтинг от",
            style = semiboldTextStyle.copy(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SliderItem(starsFloatState, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SliderItem(
    starsFloatState: MutableFloatState,
    onAction: (SearchFiltersAction) -> Unit
) {
    val items = listOf("1.0", "2.0", "3.0", "4.0", "5.0")
    val multiplier = 100f

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .rotate(180f),
            value = starsFloatState.floatValue,
            interactionSource = interactionSource,
            valueRange = 0f..(items.size - 1) * multiplier,
            steps = (items.size - 2) * multiplier.toInt(),
            onValueChange = {
                starsFloatState.floatValue = it
            },
            onValueChangeFinished = {
                val sliderValue = starsFloatState.floatValue
                val step = sliderValue.toInt() / multiplier.toInt()
                val g1 = abs(sliderValue - step * multiplier)
                val g2 = abs(sliderValue - (step - 1) * multiplier)
                val g3 = abs(sliderValue - (step + 1) * multiplier)
                val closest = min(g1, min(g2, g3))
                when (closest) {
                    g1 -> onAction(SearchFiltersAction.OnStarChanged(step * multiplier))
                    g2 -> onAction(SearchFiltersAction.OnStarChanged((step - 1) * multiplier))
                    g3 -> onAction(SearchFiltersAction.OnStarChanged((step + 1) * multiplier))
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
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 5) {
                Text(
                    text = items[i],
                    style = mediumTextStyle.copy(
                        fontSize = 14.sp,
                        color = if (abs(i.toFloat() * multiplier - 400f) == starsFloatState.floatValue) Color.Black else Color(
                            0xFF959595
                        )
                    )
                )
            }
        }
    }
}
