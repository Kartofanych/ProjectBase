package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.GeoPointDto
import com.example.travelling.common.data.models.network.ObjectType
import com.example.travelling.common.data.models.network.RateListObjectDto
import com.example.travelling.common.data.models.network.ReviewDto
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
    val activityBlocks: List<CloseObjectsBlockDto>,

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
    data class CloseObjectsBlockDto(

        @SerializedName("title")
        val title: String,

        @SerializedName("items")
        val items: List<CloseObjectDto>,
    ) {
        @Keep
        data class CloseObjectDto(

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

            @SerializedName("type")
            val type: ObjectType,
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
