package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.example.multimodulepractice.common.data.models.network.GeoPointDto

@Keep
data class CityDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("shape")
    val points: List<GeoPointDto>
)
