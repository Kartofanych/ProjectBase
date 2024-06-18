package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName
import com.example.common.models.network.GeoPointDto

data class CityDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("shape")
    val points: List<GeoPointDto>

)
