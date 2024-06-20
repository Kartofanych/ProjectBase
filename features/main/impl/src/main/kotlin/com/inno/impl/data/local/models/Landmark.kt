package com.inno.impl.data.local.models

import androidx.compose.ui.graphics.Color


data class Landmark(
    val imageRes: List<Int>,
    val name: String,
    val address: String,
    val description: String,
    val categories: List<Pair<String, Color>>
)