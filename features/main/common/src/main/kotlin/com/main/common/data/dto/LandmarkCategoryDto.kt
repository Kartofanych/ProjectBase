package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkCategoryDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String
)