package com.search_filters.impl.data.mappers

import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.data.models.local.ActivityEntity.ActivityType
import com.example.multimodulepractice.common.data.models.network.ActivityDto
import com.example.multimodulepractice.common.data.models.network.ActivityDto.ActivityTypeDto
import com.search_filters.impl.data.models.dto.SearchResponseDto
import com.search_filters.impl.data.models.local.SearchResponse
import javax.inject.Inject

class SearchMapper @Inject constructor() {

    fun map(dto: SearchResponseDto): SearchResponse {
        return SearchResponse(
            cursor = dto.cursor,
            items = dto.items.map(::mapActivity)
        )
    }

    private fun mapActivity(dto: ActivityDto): ActivityEntity {
        return ActivityEntity(
            id = dto.id,
            icon = dto.icon,
            tag = dto.tag,
            type = when (dto.type) {
                ActivityTypeDto.ATTRACTION -> ActivityType.LANDMARK
                ActivityTypeDto.SERVICE -> ActivityType.SERVICE
            },
            description = dto.description,
            rating = dto.rating,
            starCount = dto.rating.toInt(),
            subtitle = dto.subtitle,
            title = dto.title
        )
    }
}