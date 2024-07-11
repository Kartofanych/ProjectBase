package com.example.multimodulepractice.guide.impl.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GuideRequestDto(
    @SerializedName("attraction_uid")
    val id: String
)