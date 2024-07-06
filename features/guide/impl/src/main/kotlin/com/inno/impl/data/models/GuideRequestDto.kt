package com.inno.impl.data.models

import com.google.gson.annotations.SerializedName

data class GuideRequestDto(
    @SerializedName("attraction_uid")
    val id: String
)