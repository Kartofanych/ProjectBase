package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.landmark.data.Service
import com.example.multimodulepractice.landmark.data.AttractionCategory
import com.example.multimodulepractice.landmark.data.LandmarkResponse
import com.example.multimodulepractice.landmark.data.ServiceGroup
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkCategoryDto
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.ServiceDto
import com.example.multimodulepractice.main.impl.data.network.models.response.ServiceGroupDto
import javax.inject.Inject

class LandmarkMapper @Inject constructor(
    private val colorMapper: ColorMapper
) {

    fun mapResponse(response: LandmarkResponseDto): LandmarkResponse {
        return LandmarkResponse(
            id = response.id,
            name = response.name,
            info = response.info,
            address = response.address,
            categories = response.categories.map { mapCategory(it) },
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

    fun mapCategory(dto: LandmarkCategoryDto): AttractionCategory {
        return AttractionCategory(
            dto.name,
            colorMapper.mapColor(dto.color)
        )
    }
}