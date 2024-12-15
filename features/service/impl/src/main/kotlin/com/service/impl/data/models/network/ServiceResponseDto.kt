package com.service.impl.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
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
    val organization: ServiceOrganizationDto,

    @SerializedName("contacts")
    val contacts: List<ContactDto>?,
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
}
