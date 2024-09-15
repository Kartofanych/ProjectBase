package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.example.multimodulepractice.common.data.models.network.GeoPointDto

@Keep
data class MapLandmarkDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("geo_point")
    val geoPoint: GeoPointDto,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("color")
    val color: String,

    @SerializedName("distance")
    val distance: Float,

    @SerializedName("categories")
    val categoryIds: List<String>
)
