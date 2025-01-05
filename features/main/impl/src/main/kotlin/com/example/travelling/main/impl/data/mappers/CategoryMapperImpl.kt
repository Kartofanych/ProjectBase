package com.example.travelling.main.impl.data.mappers

import com.example.travelling.common.data.mappers.CategoryMapper
import com.example.travelling.common.data.models.local.AttractionCategory
import com.example.travelling.common.data.models.network.CategoryDto
import com.example.travelling.common.domain.ColorMapper
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