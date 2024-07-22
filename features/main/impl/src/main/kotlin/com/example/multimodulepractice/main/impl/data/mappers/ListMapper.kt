package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction
import com.example.multimodulepractice.main.impl.data.local_models.list.RecommendationsResponse
import com.example.multimodulepractice.main.impl.data.network.models.response.AttractionDto
import com.example.multimodulepractice.main.impl.data.network.models.response.RecommendationsResponseDto
import java.text.DecimalFormat
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val landmarkMapper: LandmarkMapper
) {

    fun mapResponse(dto: RecommendationsResponseDto): RecommendationsResponse {
        return RecommendationsResponse(
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