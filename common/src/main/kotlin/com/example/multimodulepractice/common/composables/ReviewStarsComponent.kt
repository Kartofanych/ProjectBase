package com.example.multimodulepractice.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.R
import com.example.multimodulepractice.common.theme.semiboldTextStyle

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

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .background(color = Color(0xFF74A3FF), shape = CircleShape)
                .padding(horizontal = 7.dp, vertical = 3.dp)
                .height(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            for (i in 0 until starCount) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Color.White
                )
                if (i != starCount - 1) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}
