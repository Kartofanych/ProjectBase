package com.list.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PromoCodesResponseDto(
    @SerializedName("promocodes")
    val list: List<PromoCodeDto>
)

@Keep
data class PromoCodeDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("rating")
    val rating: Float,
)
