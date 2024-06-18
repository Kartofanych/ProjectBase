package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName
import com.example.common.models.network.GeoPointDto

data class MapLandmarkDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("geo_point")
    val geoPoint: GeoPointDto,

    @SerializedName("icon")
    val icon: String
)
