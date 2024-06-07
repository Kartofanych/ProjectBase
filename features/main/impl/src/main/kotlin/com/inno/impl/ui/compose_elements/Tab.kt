package com.inno.impl.ui.compose_elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.inno.impl.data.models.MainTab

@Composable
fun Tab(currentTab: MutableState<MainTab>, tabValue: MainTab) {
    val color by animateColorAsState(targetValue = if (currentTab.value == tabValue) Color.White else Color.Gray)
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(56.dp)
            .background(color)
            .clickable {
                currentTab.value = tabValue
            }
    )
}