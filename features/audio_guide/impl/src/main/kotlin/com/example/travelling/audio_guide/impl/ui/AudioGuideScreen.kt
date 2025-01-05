package com.example.travelling.audio_guide.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.regularTextStyle

@Composable
fun AudioGidScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Скоро здесь появится\nАудио гид",
            style = regularTextStyle.copy(fontSize = 14.sp, color = Color(0xFF838383)),
            textAlign = TextAlign.Center
        )
    }
}