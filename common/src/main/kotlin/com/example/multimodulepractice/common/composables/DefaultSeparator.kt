package com.example.multimodulepractice.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSeparator(
    height: Dp = 1.dp,
    color: Color = Color(0x29000000)
) {
    Box(
        Modifier
            .height(height)
            .fillMaxWidth()
            .background(color = color)
    )
}