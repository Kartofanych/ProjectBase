package com.start.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.start.impl.ui.StartAction

@Composable
fun GuestsLoginButton(onAction: (StartAction) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxWidth()
            .height(42.dp)
            .touchAction {
                onAction(StartAction.OnGuestLogin)
            }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F2F5))
    ) {
        Text(
            text = "Войти как гость",
            style = mediumTextStyle.copy(color = Color.Black, fontSize = 13.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}