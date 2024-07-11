package com.example.multimodulepractice.login.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.common.theme.mediumTextStyle

@Composable
fun Separator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
                .background(Color(0xFFECEEF7))
        )
        Text(
            text = "или",
            style = mediumTextStyle.copy(color = Color(0xFF85889E)),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(1.dp)
                .background(Color(0xFFECEEF7))
        )

    }
}