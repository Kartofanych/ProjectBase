package com.main.common.data.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
data class CityDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("shape")
    val points: List<GeoPointDto>
)
