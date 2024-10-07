package com.search.impl.data.mappers

import com.search.impl.data.models.dto.ActivityDto
import com.search.impl.data.models.dto.ActivityGroupDto
import com.search.impl.data.models.dto.AttractionDto
import com.search.impl.data.models.dto.RecommendationsResponseDto
import com.search.impl.data.models.local.Activity
import com.search.impl.data.models.local.ActivityGroup
import com.search.impl.data.models.local.Attraction
import com.search.impl.data.models.local.ListResponse
import javax.inject.Inject

class ListMapper @Inject constructor() {

    fun mapResponse(dto: RecommendationsResponseDto): ListResponse {
        return ListResponse(
            hint = dto.hint,
            attractions = dto.attractions.map(::mapAttractionDto),
            activityGroups = dto.activityGroups.map(::mapActivityGroup),
        )
    }

    private fun mapActivityGroup(dto: ActivityGroupDto): ActivityGroup {
        return ActivityGroup(
            title = dto.title,
            activities = dto.activities.map(::mapActivity),
        )
    }

    private fun mapActivity(dto: ActivityDto): Activity {
        return Activity(
            id = dto.id,
            title = dto.title,
            subtitle = dto.subtitle,
            icon = dto.icon,
            rating = dto.rating,
            starsCount = maxOf(1, dto.rating.toInt())
        )
    }

    private fun mapAttractionDto(dto: AttractionDto): Attraction {
        return Attraction(
            id = dto.id,
            name = dto.name,
            icon = dto.icon,
            rating = dto.rating,
            stars = maxOf(1, dto.rating.toInt()),
            type = dto.type
        )
    }
}