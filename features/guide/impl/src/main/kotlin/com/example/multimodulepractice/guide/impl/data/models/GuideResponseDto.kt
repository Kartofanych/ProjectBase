package com.example.multimodulepractice.guide.impl.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GuideResponseDto(
    @SerializedName("topics")
    val topics: List<TopicDto>
)