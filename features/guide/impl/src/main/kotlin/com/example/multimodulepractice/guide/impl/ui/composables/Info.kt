package com.example.multimodulepractice.guide.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.common.utils.markdown.MarkdownText

@Composable
fun Info(text: String, index: Int) {
    val shape = when {
        (index == 0) -> RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
        else -> RoundedCornerShape(28.dp)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .background(color = Color.White, shape = shape)
            .clip(shape)
    ) {
        MarkdownText(
            markdown = text,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        )
    }
}
