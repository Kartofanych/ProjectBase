package com.example.multimodulepractice.landmark.ui.landmark_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle

@Composable
fun Chip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = color, shape = RoundedCornerShape(7.dp))
            .padding(horizontal = 13.dp, vertical = 3.dp),
    ) {
        Text(
            text = text,
            color = Color.White,
            style = semiboldTextStyle.copy(fontSize = 12.sp),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
    }
}
