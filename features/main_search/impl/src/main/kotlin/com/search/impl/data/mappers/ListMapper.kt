package com.search.impl.data.mappers

import com.main.common.data.local.Attraction
import com.search.impl.data.models.ListResponse
import com.main.common.data.dto.AttractionDto
import com.main.common.data.dto.RecommendationsResponseDto
import com.main.common.domain.LandmarkMapper
import java.text.DecimalFormat
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val landmarkMapper: LandmarkMapper
) {

    fun mapResponse(dto: RecommendationsResponseDto): ListResponse {
        return ListResponse(
            dto.popularList.map { mapAttractionDto(it) },
            dto.closeList.map { mapAttractionDto(it) }
        )
    }

    private fun mapAttractionDto(dto: AttractionDto): Attraction {
        val formatter = DecimalFormat("#.##")
        val distance = formatter.format(dto.distance)

        return Attraction(
            id = dto.id,
            name = dto.name,
            distance = "$distance км от вас",
            icon = dto.icon,
            shortInfo = dto.shortInfo,
            categories = dto.categories.map { landmarkMapper.mapCategory(it) },
            dateCreation = dto.dateCreation
        )
    }
}