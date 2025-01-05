package com.example.travelling.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String
)