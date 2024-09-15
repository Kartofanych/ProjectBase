package com.example.multimodulepractice.common.data.mappers

import com.example.multimodulepractice.common.data.models.local.AttractionCategory
import com.example.multimodulepractice.common.data.models.network.CategoryDto

interface CategoryMapper {

    fun mapCategory(dto: CategoryDto): AttractionCategory
}