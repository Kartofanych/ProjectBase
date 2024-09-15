package com.attraction.impl.data.mappers

import com.attraction.impl.data.models.dto.LandmarkResponseDto
import com.attraction.impl.data.models.dto.ServiceDto
import com.attraction.impl.data.models.dto.ServiceGroupDto
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.data.models.local.Service
import com.attraction.impl.data.models.local.ServiceGroup
import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import javax.inject.Inject

class AttractionMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) {

    fun mapResponse(response: LandmarkResponseDto): Attraction {
        return Attraction(
            id = response.id,
            name = response.name,
            info = response.info,
            address = response.address,
            categories = response.categories.map { categoryMapper.mapCategory(it) },
            images = response.images.map { it.url },
            serviceGroups = response.serviceGroups.map { mapServiceGroup(it) }
        )
    }

    private fun mapServiceGroup(dto: ServiceGroupDto): ServiceGroup {
        return ServiceGroup(
            title = dto.title,
            subtitle = dto.subtitle,
            services = dto.services.map { mapService(it) }
        )
    }

    private fun mapService(dto: ServiceDto): Service {
        return Service(
            id = dto.id,
            title = dto.title,
            subtitle = dto.subtitle,
            icon = dto.icon,
            price = dto.price,
            rating = dto.rating,
            organizationId = dto.organizationId,
            isIconOrganization = dto.isIconOrganization
        )
    }
}