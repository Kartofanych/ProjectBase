package com.inno.impl.data.mappers

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import javax.inject.Inject

class ColorMapper @Inject constructor() {

    fun mapColor(color: String): Color {
        return Color(mapColorToInt(color))
    }

    fun mapColorToInt(color: String): Int {
        return color.toColorInt()
    }

}