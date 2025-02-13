package com.example.travelling.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = CircleShape,
    backgroundColor: Color = Color(0xFF74A3FF),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .touchAction(onClick)
            .then(modifier)
            .background(color = backgroundColor, shape = shape)
            .clip(shape),
        contentAlignment = Alignment.Center,
        content = content
    )
}