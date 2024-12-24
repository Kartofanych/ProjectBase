package com.start.impl.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.DefaultButton
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.start.impl.ui.StartAction

@Composable
fun LoginButton(onAction: (StartAction) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 14.dp)
            .height(54.dp)
            .touchAction { onAction(StartAction.OnLogin) }
    ) {
        DefaultButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(end = 14.dp)
                .fillMaxWidth()
                .height(42.dp),
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color(0xFF74A3FF)
        ) {
            Text(
                text = "Войти",
                style = mediumTextStyle.copy(color = Color.White, fontSize = 13.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        DefaultButton(
            modifier = Modifier
                .height(21.dp)
                .width(104.dp)
                .align(Alignment.TopEnd)
                .rotate(10f),
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xFF404040)
        ) {
            Text(
                text = "Промо в подарок!",
                style = mediumTextStyle.copy(color = Color.White, fontSize = 8.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
