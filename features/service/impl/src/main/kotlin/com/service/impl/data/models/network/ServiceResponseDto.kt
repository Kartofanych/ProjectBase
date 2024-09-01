package com.service.impl.data.models.network

import com.google.gson.annotations.SerializedName

class ServiceResponseDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("rating_block")
    val ratingBlock: RatingBlockDto,

    @SerializedName("price")
    val price: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("organization")
    val organization: ServiceOrganizationDto
) {
    class RatingBlockDto(
        @SerializedName("rating")
        val rating: Float,
        @SerializedName("review_count")
        val reviewCount: Int
    )

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
}
