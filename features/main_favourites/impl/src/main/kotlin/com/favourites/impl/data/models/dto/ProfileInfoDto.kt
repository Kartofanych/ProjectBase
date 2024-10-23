package com.favourites.impl.data.models.dto

import com.google.gson.annotations.SerializedName

class ProfileInfoDto(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("mail")
    val mail: String,
)