package com.example.multimodulepractice.main.impl.data.mappers

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.multimodulepractice.common.domain.ColorMapper
import javax.inject.Inject

class ColorMapperImpl @Inject constructor() : ColorMapper {

    override fun mapColor(color: String): Color {
        return Color(mapColorToInt(color))
    }

    override fun mapColorToInt(color: String): Int {
        return color.toColorInt()
    }
}