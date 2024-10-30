package com.splash.impl.data.models.dto

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
class CityDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("position")
    val geoPoint: GeoPointDto,

    @SerializedName("radius")
    val radius: Float,

    @SerializedName("icon")
    val icon: String
)