package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.example.multimodulepractice.common.models.network.GeoPointDto

@Keep
data class CityDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("shape")
    val points: List<GeoPointDto>

)
