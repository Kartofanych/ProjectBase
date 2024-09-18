package com.search.impl.data.mappers

import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.data.models.network.ActivityDto
import com.search.impl.data.models.dto.SearchResponseDto
import com.search.impl.data.models.local.SearchResponse
import javax.inject.Inject

class SearchMapper @Inject constructor() {

    fun map(dto: SearchResponseDto): SearchResponse {
        return SearchResponse(
            cursor = dto.cursor,
            items = dto.items.map { mapActivity(it) }
        )
    }

    private fun mapActivity(dto: ActivityDto): ActivityEntity {
        return ActivityEntity(
            id = dto.id,
            icon = dto.icon,
            tag = dto.tag,
            type = when (dto.type) {
                ActivityDto.ActivityTypeDto.ATTRACTION -> ActivityEntity.ActivityType.LANDMARK
                ActivityDto.ActivityTypeDto.SERVICE -> ActivityEntity.ActivityType.SERVICE
            },
            description = dto.description,
            rating = dto.rating,
            starCount = maxOf(1, dto.rating.toInt()),
            subtitle = dto.subtitle,
            title = dto.title
        )
    }
}