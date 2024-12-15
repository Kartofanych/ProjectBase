package com.attraction.impl.data.mappers

import com.attraction.impl.data.models.dto.LandmarkResponseDto
import com.attraction.impl.data.models.dto.LandmarkResponseDto.ActivitiesBlockDto
import com.attraction.impl.data.models.dto.LandmarkResponseDto.ReviewsBlockDto
import com.attraction.impl.data.models.dto.LandmarkResponseDto.ScheduleDto.DayScheduleDto
import com.attraction.impl.data.models.dto.LandmarkResponseDto.SimilarItemDto
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.data.models.local.Attraction.ReviewBlock
import com.attraction.impl.data.models.local.Service
import com.attraction.impl.data.models.local.SimilarAttraction
import com.example.multimodulepractice.common.data.models.local.RatingBlock
import com.example.multimodulepractice.common.data.models.local.Review
import com.example.multimodulepractice.common.utils.withWordEnding
import javax.inject.Inject

class AttractionMapper @Inject constructor() {

    fun mapResponse(response: LandmarkResponseDto): Attraction {
        return Attraction(
            id = response.id,
            images = response.images.map { it.url },
            isLiked = response.isLiked,
            infoBlock = Attraction.InfoBlock(
                name = response.name,
                ratingBlock = mapRatingBlock(response.reviews),
                address = response.address,
                scheduleStatus = with(response.schedule.status) {
                    Attraction.InfoBlock.ScheduleStatus(
                        status = when {
                            isOpen -> "Открыто сейчас"
                            else -> "Закрыто сейчас"
                        },
                        timing = subtitle
                    )
                }
            ),
            servicesBlock = response.activityBlocks.map(::mapActivityBlock),
            reviewsBlock = mapReviewsBlock(response.reviews),
            similarBlock = Attraction.SimilarBlock(
                items = response.similar.map(::mapSimilarAttraction)
            ),
            schedule = Attraction.Schedule(
                isVisible = false,
                scheduleDays = response.schedule.days.map(::mapScheduleDay)
            )
        )
    }

    private fun mapScheduleDay(dto: DayScheduleDto): Attraction.Schedule.ScheduleDay =
        with(dto) { Attraction.Schedule.ScheduleDay(name = name, timing = timing) }

    private fun mapSimilarAttraction(dto: SimilarItemDto): SimilarAttraction =
        SimilarAttraction(
            id = dto.id,
            name = dto.title,
            icon = dto.icon,
            rating = dto.rating,
            stars = dto.rating.toInt(),
            type = dto.category
        )

    private fun mapReviewsBlock(dto: ReviewsBlockDto): ReviewBlock =
        with(dto) {
            ReviewBlock(
                ratingBlock = RatingBlock(
                    rating = rating,
                    reviewCount = withWordEnding(
                        number = total,
                        nominative = "1 отзыв",
                        genitiveSingular = "$total отзыва",
                        genitivePlural = "$total отзывов",
                    ),
                    starCount = rating.toInt()
                ),
                reviewCounts = with(rateList) {
                    listOf(five, four, three, two, one)
                },
                reviews = reviewList.map {
                    Review(
                        title = it.author.name,
                        subtitle = it.author.email,
                        date = it.date,
                        //TODO from backend
                        icon = "https://www.kino-teatr.ru/art/7352/117986.jpg",
                        text = it.text,
                        stars = it.stars,
                    )
                },
                reviewsNumber = total
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
                starCount = rating.toInt()
            )
        }

    private fun mapActivityBlock(dto: ActivitiesBlockDto): Attraction.ServicesBlock =
        with(dto) {
            Attraction.ServicesBlock(
                title = title,
                services = items.map { item ->
                    Service(
                        id = item.id,
                        title = item.title,
                        subtitle = item.subtitle,
                        icon = item.icon,
                        distance = item.distance,
                        rating = item.rating,
                        starsCount = item.rating.toInt()
                    )
                }
            )
        }
}