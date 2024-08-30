package com.service.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.TopShadow
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.service.impl.data.models.local.Service


@Composable
fun ServiceFooter(modifier: Modifier, service: Service) {
    Column(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {

        TopShadow(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(),
            alpha = 0.2f
        )

        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 12.dp),
                text = service.price,
                style = mediumTextStyle.copy(fontSize = 18.sp, color = Color(0xFF74A3FF))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 12.dp)
                    .background(
                        color = Color(0xFF74A3FF),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {

                    }
                    .padding(
                        horizontal = 22.dp,
                        vertical = 8.dp
                    )
            ) {
                Text(
                    text = "Связаться с нами",
                    modifier = Modifier.align(Alignment.Center),
                    style = mediumTextStyle.copy(fontSize = 12.sp, color = Color.White)
                )
            }
        }
    }
}
