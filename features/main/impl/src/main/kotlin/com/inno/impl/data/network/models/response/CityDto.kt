package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName
import com.inno.impl.data.network.models.request.GeoPointDto

data class CityDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("points")
    val points: List<GeoPointDto>

)
