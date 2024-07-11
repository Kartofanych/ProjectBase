package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkResponseDto(

    @SerializedName("uid")
    val id: String,

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