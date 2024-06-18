package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class MapInfoResponseDto(
    @SerializedName("city")
    val city: CityDto,

    @SerializedName("attractions")
    val list: List<MapLandmarkDto>
)