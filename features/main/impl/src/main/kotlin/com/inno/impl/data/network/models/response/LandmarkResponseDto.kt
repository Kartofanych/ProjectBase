package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class LandmarkResponseDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("short_info")
    val info: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("images")
    val images: List<ImageDto>,

    @SerializedName("categories")
    val categories: List<LandmarkCategoryDto>,

    @SerializedName("audio_guide")
    val audioGuides: List<LandmarkAudioGidDto>

)