package com.reviews.impl.data

import com.example.multimodulepractice.common.data.models.local.RatingBlock
import com.example.multimodulepractice.common.data.models.local.Review
import com.example.multimodulepractice.common.data.models.network.ReviewDto
import com.example.multimodulepractice.common.utils.withWordEnding
import com.reviews.impl.data.models.dto.ReviewsListResponseDto
import com.reviews.impl.data.models.dto.ReviewsPageResponseDto
import com.reviews.impl.data.models.local.ReviewsListResponse
import com.reviews.impl.data.models.local.ReviewsPageResponse
import javax.inject.Inject

class ReviewsMapper @Inject constructor() {

    fun mapList(dto: ReviewsListResponseDto): ReviewsListResponse {
        return ReviewsListResponse(
            items = dto.items.map(::mapReview),
            cursor = dto.cursor
        )
    }

    fun mapPage(dto: ReviewsPageResponseDto): ReviewsPageResponse {
        return ReviewsPageResponse(
            ratingBlock = with(dto.reviewsBlockDto) {
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
            },
            cursor = dto.cursor,
            title = dto.title,
            rateList = with(dto.reviewsBlockDto.ratings) {
                listOf(five, four, three, two, one)
            },
            items = dto.reviewsBlockDto.items.map(::mapReview)
        )
    }

    private fun mapReview(dto: ReviewDto): Review {
        return Review(
            title = dto.author.name,
            subtitle = dto.author.email,
            date = dto.date,
            icon = dto.author.icon,
            text = dto.text,
            stars = dto.stars,
        )
    }
}
