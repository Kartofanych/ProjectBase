package com.favourites.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.semiboldTextStyle

@Composable
fun CategoryChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .height(14.dp),
    ) {
        Text(
            text = text,
            color = Color.White,
            style = semiboldTextStyle.copy(fontSize = 10.sp),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
    }
}
