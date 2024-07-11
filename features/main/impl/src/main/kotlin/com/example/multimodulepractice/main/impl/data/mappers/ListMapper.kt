package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.main.impl.data.local_models.list.CloseAttraction
import com.example.multimodulepractice.main.impl.data.local_models.list.RecommendationsResponse
import com.example.multimodulepractice.main.impl.data.local_models.list.VerticalAttraction
import com.example.multimodulepractice.main.impl.data.network.models.response.CloseAttractionDto
import com.example.multimodulepractice.main.impl.data.network.models.response.RecommendationsResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.VerticalAttractionDto
import java.text.DecimalFormat
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val landmarkMapper: LandmarkMapper
) {

    fun mapResponse(dto: RecommendationsResponseDto): RecommendationsResponse {
        return RecommendationsResponse(
            dto.popularList.map { mapVerticalAttractionDto(it) },
            dto.closeList.map { mapCloseAttractionDto(it) }
        )
    }

    private fun mapVerticalAttractionDto(dto: VerticalAttractionDto): VerticalAttraction {
        val formatter = DecimalFormat("#.##")
        val distance = formatter.format(dto.distance)

        return VerticalAttraction(
            id = dto.id,
            name = dto.name,
            distance = "$distance км от вас",
            icon = dto.icon
        )
    }

    private fun mapCloseAttractionDto(dto: CloseAttractionDto): CloseAttraction {
        val formatter = DecimalFormat("#.##")
        val distance = formatter.format(dto.distance)

        return CloseAttraction(
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