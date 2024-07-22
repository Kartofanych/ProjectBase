package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AttractionDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("distance")
    val distance: Float,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("short_info")
    val shortInfo: String,

    @SerializedName("categories")
    val categories: List<LandmarkCategoryDto>,

    @SerializedName("date_of_creation")
    val dateCreation: String
)

