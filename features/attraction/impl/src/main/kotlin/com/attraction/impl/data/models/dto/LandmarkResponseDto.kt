package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkResponseDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("location")
    val location: GeoPointDto,

    @SerializedName("address")
    val address: String,

    @SerializedName("images")
    val images: List<ImageDto>,

    @SerializedName("reviews")
    val reviews: ReviewsBlockDto,

    @SerializedName("schedule")
    val schedule: ScheduleDto,

    @SerializedName("is_liked")
    val isLiked: Boolean,

    @SerializedName("theme_blocks")
    val activityBlocks: List<ActivitiesBlockDto>,

    @SerializedName("similar_places")
    val similar: List<SimilarItemDto>
) {
    @Keep
    data class ReviewsBlockDto(
        @SerializedName("avg_rate")
        val rating: Float,

        @SerializedName("total_count")
        val total: Int,

        @SerializedName("rate_list")
        val rateList: RateListObjectDto,

        @SerializedName("review_list")
        val reviewList: List<ReviewDto>,
    )

    @Keep
    data class ReviewDto(

        @SerializedName("uid")
        val id: String,

        @SerializedName("description")
        val text: String,

        @SerializedName("rate")
        val stars: Int,

        @SerializedName("date")
        val date: String,

        @SerializedName("author")
        val author: ReviewAuthor,
    )

    @Keep
    data class ReviewAuthor(
        @SerializedName("uid")
        val id: String,

        @SerializedName("username")
        val name: String,

        @SerializedName("email")
        val email: String,
    )

    @Keep
    data class RateListObjectDto(
        @SerializedName("1")
        val one: Int,

        @SerializedName("2")
        val two: Int,

        @SerializedName("3")
        val three: Int,

        @SerializedName("4")
        val four: Int,

        @SerializedName("5")
        val five: Int,
    )

    @Keep
    data class ScheduleDto(
        @SerializedName("status")
        val status: StatusDto,

        @SerializedName("days")
        val days: List<DayScheduleDto>
    ) {
        @Keep
        data class StatusDto(
            @SerializedName("is_open")
            val isOpen: Boolean,

            @SerializedName("subtitle")
            val subtitle: String,
        )

        @Keep
        data class DayScheduleDto(
            @SerializedName("name")
            val name: String,

            @SerializedName("timing")
            val timing: String,
        )
    }

    @Keep
    data class ActivitiesBlockDto(

        @SerializedName("title")
        val title: String,

        @SerializedName("items")
        val items: List<ActivityDto>,
    ) {
        @Keep
        data class ActivityDto(

            @SerializedName("uid")
            val id: String,

            @SerializedName("title")
            val title: String,

            @SerializedName("subtitle")
            val subtitle: String,

            @SerializedName("icon")
            val icon: String,

            @SerializedName("rating")
            val rating: Float,

            @SerializedName("distance")
            val distance: String,
        )
    }

    @Keep
    data class SimilarItemDto(

        @SerializedName("uid")
        val id: String,

        @SerializedName("icon")
        val icon: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("rating")
        val rating: Float,

        @SerializedName("category")
        val category: String
    )
}
