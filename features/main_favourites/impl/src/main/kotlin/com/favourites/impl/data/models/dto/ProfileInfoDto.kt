package com.favourites.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ProfileInfoDto(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("mail")
    val mail: String,
)