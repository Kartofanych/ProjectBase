package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class StartInfoResponse(
    @SerializedName("city")
    val city: CityDto,

    @SerializedName("list")
    val list: List<MapLandmarkDto>
)