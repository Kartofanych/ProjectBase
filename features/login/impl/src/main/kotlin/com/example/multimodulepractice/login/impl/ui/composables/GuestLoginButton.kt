package com.example.multimodulepractice.login.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.common.theme.semiboldTextStyle

@Composable
fun GuestsLoginButton(navigateToMain: () -> Unit) {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color(0xFFF1F2F5))
            .clickable {
                navigateToMain()
            }
    ) {
        Text(
            text = "Войти как гость",
            style = semiboldTextStyle.copy(color = Color.Black),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}