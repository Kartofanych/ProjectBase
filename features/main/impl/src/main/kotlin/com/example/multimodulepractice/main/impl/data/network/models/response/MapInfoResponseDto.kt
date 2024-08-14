package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MapInfoResponseDto(
    @SerializedName("city")
    val city: CityDto,

    @SerializedName("landmarks")
    val list: List<MapLandmarkDto>,
)