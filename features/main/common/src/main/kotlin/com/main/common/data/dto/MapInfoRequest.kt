package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class MapInfoRequest(
    @SerializedName("city_uid")
    val id: String
)
