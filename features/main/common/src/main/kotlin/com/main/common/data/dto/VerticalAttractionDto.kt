package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VerticalAttractionDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("distance")
    val distance: Float,

    @SerializedName("icon")
    val icon: String

)