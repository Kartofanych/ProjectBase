package com.inno.impl.ui.fragments.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ListContent() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green))
}