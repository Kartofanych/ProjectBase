package com.example.travelling.common.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.semiboldTextStyle

@Composable
fun ReviewStarsComponent(rating: String, starCount: Int, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rating,
            style = semiboldTextStyle.copy(
                color = Color(0xFF74A3FF),
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.width(6.dp))

        StarsComponent(starCount)
    }
}
