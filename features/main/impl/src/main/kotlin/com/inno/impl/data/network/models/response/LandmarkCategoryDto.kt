package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class LandmarkCategoryDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String
)