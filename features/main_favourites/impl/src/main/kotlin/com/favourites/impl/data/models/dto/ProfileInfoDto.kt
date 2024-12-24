package com.favourites.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ProfileInfoDto(
    @SerializedName("image_url")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("promocodes_count")
    val promoCodesCount: Int,
)