package com.main.common.data.dto

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.network.CategoryDto
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
    val categories: List<CategoryDto>,

    @SerializedName("date_of_creation")
    val dateCreation: String
)

