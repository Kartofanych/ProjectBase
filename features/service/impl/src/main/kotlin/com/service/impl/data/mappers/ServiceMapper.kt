package com.service.impl.data.mappers

import com.example.travelling.common.data.models.local.RatingBlock
import com.service.impl.data.models.local.Service
import com.service.impl.data.models.local.Service.Contact
import com.service.impl.data.models.local.Service.ServiceOrganization
import com.service.impl.data.models.network.ServiceResponseDto
import com.service.impl.data.models.network.ServiceResponseDto.ContactDto
import com.service.impl.data.models.network.ServiceResponseDto.RatingBlockDto
import com.service.impl.data.models.network.ServiceResponseDto.ServiceOrganizationDto
import javax.inject.Inject

class ServiceMapper @Inject constructor() {

    fun map(dto: ServiceResponseDto): Service {
        return Service(
            id = dto.id,
            title = dto.title,
            subtitle = dto.subtitle,
            description = dto.description,
            images = dto.images,
            organization = mapOrganization(dto.organization),
            price = dto.price,
            ratingBlock = mapRatingBlock(dto.ratingBlock),
            contacts = dto.contacts?.map { mapContact(it) } ?: emptyList()
        )
    }

    private fun mapRatingBlock(dto: RatingBlockDto): RatingBlock {
        val num100 = dto.reviewCount % 100
        val num10 = dto.reviewCount % 10
        return RatingBlock(
            rating = dto.rating,
            reviewCount = when {
                num100 in 11..19 -> "${dto.reviewCount} отзывов"
                num10 == 1 -> "${dto.reviewCount} отзыв"
                num10 in 2..4 -> "${dto.reviewCount} отзыва"
                else -> "${dto.reviewCount} отзывов"
            },
            starCount = maxOf(1, dto.rating.toInt()),
            total = dto.reviewCount
        )
    }

    private fun mapOrganization(dto: ServiceOrganizationDto): ServiceOrganization {
        return ServiceOrganization(
            id = dto.id,
            icon = dto.icon,
            name = dto.name,
            rating = dto.rating
        )
    }

    private fun mapContact(dto: ContactDto): Contact {
        return Contact(
            deeplink = dto.deeplink,
            title = dto.title,
            icon = dto.icon
        )
    }
}