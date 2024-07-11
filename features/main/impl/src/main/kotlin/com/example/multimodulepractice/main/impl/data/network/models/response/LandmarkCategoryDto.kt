package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkCategoryDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String
)