package com.example.multimodulepractice.main.impl.data.mappers

import com.main.common.data.dto.LandmarkCategoryDto
import com.main.common.data.dto.LandmarkResponseDto
import com.main.common.data.dto.ServiceDto
import com.main.common.data.dto.ServiceGroupDto
import com.main.common.data.local.AttractionCategory
import com.main.common.data.local.LandmarkResponse
import com.main.common.data.local.Service
import com.main.common.data.local.ServiceGroup
import com.main.common.domain.ColorMapper
import com.main.common.domain.LandmarkMapper
import javax.inject.Inject

class LandmarkMapperImpl @Inject constructor(
    private val colorMapper: ColorMapper
) : LandmarkMapper {

    override fun mapResponse(response: LandmarkResponseDto): LandmarkResponse {
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

    override fun mapCategory(dto: LandmarkCategoryDto): AttractionCategory {
        return AttractionCategory(
            dto.name,
            colorMapper.mapColor(dto.color)
        )
    }
}