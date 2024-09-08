package com.main.common.domain

import com.main.common.data.dto.LandmarkCategoryDto
import com.main.common.data.dto.LandmarkResponseDto
import com.main.common.data.local.AttractionCategory
import com.main.common.data.local.LandmarkResponse

interface LandmarkMapper {

    fun mapResponse(response: LandmarkResponseDto): LandmarkResponse

    fun mapCategory(dto: LandmarkCategoryDto): AttractionCategory
}