package com.service.impl.data.mappers

import com.example.travelling.common.data.models.local.RatingBlock
import com.example.travelling.common.data.models.local.Review
import com.example.travelling.common.data.models.network.ReviewsBlockDto
import com.example.travelling.common.data.models.network.ScheduleDto
import com.example.travelling.common.utils.withWordEnding
import com.service.impl.data.models.local.Service
import com.service.impl.data.models.local.Service.Contact
import com.service.impl.data.models.local.Service.ServiceOrganization
import com.service.impl.data.models.network.ServiceResponseDto
import com.service.impl.data.models.network.ServiceResponseDto.ContactDto
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
            reviewsBlock = mapReviewsBlock(dto.reviews),
            infoBlock = mapInfoBlock(dto),
            schedule = Service.Schedule(
                scheduleDays = dto.schedule.days.map(::mapScheduleDay)
            ),
            serviceTypes = dto.serviceTypes.map(::mapServiceType)
        )
    }

    private fun mapServiceType(dto: ServiceResponseDto.ServiceTypeDto): Service.ServiceType {
        return Service.ServiceType(
            id = dto.id,
            title = dto.title,
            price = dto.price,
        )
    }

    private fun mapScheduleDay(dto: ScheduleDto.DayScheduleDto): Service.Schedule.ScheduleDay =
        with(dto) { Service.Schedule.ScheduleDay(name = name, timing = timing) }

    private fun mapInfoBlock(dto: ServiceResponseDto): Service.InfoBlock {
        return Service.InfoBlock(
            name = dto.title,
            ratingBlock = mapRatingBlock(dto.reviews),
            address = dto.subtitle,
            scheduleStatus = with(dto.schedule.status) {
                Service.InfoBlock.ScheduleStatus(
                    status = when {
                        isOpen -> "Открыто сейчас"
                        else -> "Закрыто сейчас"
                    },
                    timing = subtitle,
                )
            },
            contacts = dto.contacts?.map(::mapContact) ?: emptyList(),
        )
    }

    private fun mapReviewsBlock(dto: ReviewsBlockDto): Service.ReviewBlock =
        with(dto) {
            Service.ReviewBlock(
                ratingBlock = RatingBlock(
                    rating = rating,
                    reviewCount = withWordEnding(
                        number = total,
                        nominative = "1 отзыв",
                        genitiveSingular = "$total отзыва",
                        genitivePlural = "$total отзывов",
                    ),
                    starCount = rating.toInt(),
                    total = total,
                ),
                reviewCounts = with(rateList) {
                    listOf(five, four, three, two, one)
                },
                reviews = reviewList.map {
                    Review(
                        title = it.author.name,
                        subtitle = it.author.email,
                        date = it.date,
                        icon = it.author.icon,
                        text = it.text,
                        stars = it.stars,
                    )
                }
            )
        }

    private fun mapRatingBlock(dto: ReviewsBlockDto): RatingBlock =
        with(dto) {
            RatingBlock(
                rating = rating,
                reviewCount = withWordEnding(
                    number = total,
                    nominative = "1 отзыв",
                    genitiveSingular = "$total отзыва",
                    genitivePlural = "$total отзывов",
                ),
                starCount = rating.toInt(),
                total = total,
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