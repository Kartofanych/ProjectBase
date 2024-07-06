package com.inno.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.common.theme.montserratFamily

@Composable
fun Info(text: AnnotatedString, imageHeight: MutableState<Float>) {
    Box(
        modifier = Modifier
            .padding(top = (imageHeight.value - 16).dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
    ) {
        Text(
            text = text,
            fontFamily = montserratFamily,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}