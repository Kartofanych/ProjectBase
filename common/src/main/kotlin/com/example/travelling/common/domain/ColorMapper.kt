package com.example.travelling.common.domain

import androidx.compose.ui.graphics.Color

interface ColorMapper {

    fun mapColor(color: String): Color

    fun mapColorToInt(color: String): Int
}