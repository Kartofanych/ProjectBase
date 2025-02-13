package com.service.impl.data.models.network

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.ReviewsBlockDto
import com.example.travelling.common.data.models.network.ScheduleDto
import com.google.gson.annotations.SerializedName

@Keep
class ServiceResponseDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("organization")
    val organization: ServiceOrganizationDto,

    @SerializedName("contacts")
    val contacts: List<ContactDto>?,

    @SerializedName("schedule")
    val schedule: ScheduleDto,

    @SerializedName("service_types")
    val serviceTypes: List<ServiceTypeDto>,

    @SerializedName("reviews")
    val reviews: ReviewsBlockDto,
) {
    @Keep
    class RatingBlockDto(
        @SerializedName("rating")
        val rating: Float,
        @SerializedName("review_count")
        val reviewCount: Int
    )

    @Keep
    class ServiceOrganizationDto(
        @SerializedName("uid")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("rating")
        val rating: Float
    )

    @Keep
    class ContactDto(
        @SerializedName("deeplink")
        val deeplink: String,

        @SerializedName("icon")
        val icon: String,

        @SerializedName("title")
        val title: String,
    )

    @Keep
    class ServiceTypeDto(
        @SerializedName("uid")
        val id: String,

        @SerializedName("price")
        val price: String,

        @SerializedName("title")
        val title: String,
    )
}
