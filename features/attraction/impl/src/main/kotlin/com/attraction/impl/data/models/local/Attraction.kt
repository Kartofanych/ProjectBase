package com.attraction.impl.data.models.local

import com.example.travelling.common.data.models.local.GeoPoint
import com.example.travelling.common.data.models.local.RatingBlock
import com.example.travelling.common.data.models.local.Review

data class Attraction(
    val id: String,
    val images: List<String>,
    val isLiked: Boolean,
    val infoBlock: InfoBlock,
    val closeObjectsBlock: List<CloseObjectsBlock>,
    val reviewsBlock: ReviewBlock,
    val similarBlock: SimilarBlock,
    val schedule: Schedule,
    val isAuthorized: Boolean,
    val location: GeoPoint,
) {
    class InfoBlock(
        val name: String,
        val ratingBlock: RatingBlock,
        val address: String,
        val scheduleStatus: ScheduleStatus,
        val showGuide: Boolean,
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

    class CloseObjectsBlock(
        val title: String,
        val closeObjects: List<CloseObject>,
    )

    class ReviewBlock(
        val ratingBlock: RatingBlock,
        val reviewCounts: List<Int>,
        val reviews: List<Review>,
    )

    //TODO later
    class SimilarBlock(
        val items: List<SimilarAttraction>
    )
}