package com.service.impl.data.models.local

import com.example.travelling.common.data.models.local.RatingBlock
import com.example.travelling.common.data.models.local.Review

data class Service(
    val id: String,
    val title: String,
    val subtitle: String,
    val infoBlock: InfoBlock,
    val price: String,
    val description: String,
    val images: List<String>,
    val organization: ServiceOrganization,
    val reviewsBlock: ReviewBlock,
    val schedule: Schedule,
    val serviceTypes: List<ServiceType>,
) {

    class InfoBlock(
        val name: String,
        val ratingBlock: RatingBlock,
        val address: String,
        val scheduleStatus: ScheduleStatus,
        val contacts: List<Contact>,
    ) {
        class ScheduleStatus(
            val status: String,
            val timing: String,
        )
    }

    data class Schedule(
        val scheduleDays: List<ScheduleDay>
    ) {

        class ScheduleDay(
            val name: String,
            val timing: String,
        )
    }

    data class ServiceOrganization(
        val id: String,
        val name: String,
        val icon: String,
        val rating: Float
    )

    data class Contact(
        val deeplink: String,
        val icon: String,
        val title: String,
    )

    class ReviewBlock(
        val ratingBlock: RatingBlock,
        val reviewCounts: List<Int>,
        val reviews: List<Review>,
    )

    class ServiceType(
        val id: String,
        val price: String,
        val title: String,
    )
}
