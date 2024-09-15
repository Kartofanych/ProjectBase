package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import com.example.multimodulepractice.common.data.models.local.AttractionCategory
import com.example.multimodulepractice.common.data.models.network.CategoryDto
import com.example.multimodulepractice.common.domain.ColorMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(
    private val colorMapper: ColorMapper
) : CategoryMapper {

    override fun mapCategory(dto: CategoryDto): AttractionCategory {
        return AttractionCategory(
            dto.name,
            colorMapper.mapColor(dto.color)
        )
    }
}