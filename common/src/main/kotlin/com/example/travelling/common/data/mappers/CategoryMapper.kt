package com.example.travelling.common.data.mappers

import com.example.travelling.common.data.models.local.AttractionCategory
import com.example.travelling.common.data.models.network.CategoryDto

interface CategoryMapper {

    fun mapCategory(dto: CategoryDto): AttractionCategory
}