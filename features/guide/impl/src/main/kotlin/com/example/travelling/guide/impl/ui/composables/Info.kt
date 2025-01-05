package com.example.travelling.guide.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelling.common.composables.MarkDownText

@Composable
fun Info(text: String, isLast: Boolean, isFirst: Boolean) {
    val shape = when {
        isFirst -> RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
        else -> RoundedCornerShape(28.dp)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .background(color = Color.White, shape = shape)
            .clip(shape)
    ) {

        MarkDownText(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp),
            text = text
        )
        if (isLast) {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
