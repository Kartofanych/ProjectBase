package com.example.travelling.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.semiboldTextStyle

@Composable
fun ChipCard(
    text: String,
    activeColor: Color,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    inActiveColor: Color = Color.White,
    action: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(
                color = if (isActive) activeColor else inActiveColor,
                shape = RoundedCornerShape(7.dp)
            )
            .clip(RoundedCornerShape(7.dp))
            .padding(horizontal = 13.dp, vertical = 3.dp)
            .touchAction(onClick = action),
    ) {
        Text(
            text = text,
            color = Color.White,
            style = semiboldTextStyle.copy(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
