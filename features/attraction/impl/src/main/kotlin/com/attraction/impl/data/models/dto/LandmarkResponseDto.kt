package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.network.CategoryDto
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkResponseDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("is_liked")
    val isLiked: Boolean,

    @SerializedName("short_info")
    val info: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("images")
    val images: List<ImageDto>,

    @SerializedName("categories")
    val categories: List<CategoryDto>,

    @SerializedName("service_groups")
    val serviceGroups: List<ServiceGroupDto> = emptyList()
)