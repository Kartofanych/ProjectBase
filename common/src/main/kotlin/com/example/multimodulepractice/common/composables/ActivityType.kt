package com.example.multimodulepractice.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.mediumTextStyle

@Composable
fun ActivityType(tag: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(14.dp)
            .background(Color.White, RoundedCornerShape(5.dp))
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF74A3FF)
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag,
            style = mediumTextStyle.copy(
                fontSize = 10.sp,
                color = Color(0xFF74A3FF)
            )
        )
    }
}
