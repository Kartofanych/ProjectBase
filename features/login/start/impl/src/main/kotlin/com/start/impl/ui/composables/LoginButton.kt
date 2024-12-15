package com.start.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.start.impl.ui.StartAction

@Composable
fun LoginButton(onAction: (StartAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .touchAction {
                onAction(StartAction.OnLogin)
            }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF74A3FF))
    ) {
        Text(
            text = "Войти",
            style = mediumTextStyle.copy(color = Color.White, fontSize = 13.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
