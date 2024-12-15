package com.attraction.impl.data.models.local

import com.example.multimodulepractice.common.data.models.local.RatingBlock
import com.example.multimodulepractice.common.data.models.local.Review

data class Attraction(
    val id: String,
    val images: List<String>,
    val isLiked: Boolean,
    val infoBlock: InfoBlock,
    val servicesBlock: List<ServicesBlock>,
    val reviewsBlock: ReviewBlock,
    val similarBlock: SimilarBlock,
    val schedule: Schedule,
) {
    class InfoBlock(
        val name: String,
        val ratingBlock: RatingBlock,
        val address: String,
        val scheduleStatus: ScheduleStatus,
    ) {
        class ScheduleStatus(
            val status: String,
            val timing: String,
        )
    }

    data class Schedule(
        val isVisible: Boolean,
        val scheduleDays: List<ScheduleDay>
    ) {

        class ScheduleDay(
            val name: String,
            val timing: String,
        )
    }

    class ServicesBlock(
        val title: String,
        val services: List<Service>,
    )

    class ReviewBlock(
        val ratingBlock: RatingBlock,
        val reviewCounts: List<Int>,
        val reviewsNumber: Int,
        val reviews: List<Review>,
    )

    //TODO later
    class SimilarBlock(
        val items: List<SimilarAttraction>
    )
}