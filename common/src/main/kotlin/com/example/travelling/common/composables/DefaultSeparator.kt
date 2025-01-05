package com.example.travelling.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSeparator(
    modifier: Modifier = Modifier,
    color: Color = Color(0x29000000),
) {
    Box(
        modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = color)
    )
}