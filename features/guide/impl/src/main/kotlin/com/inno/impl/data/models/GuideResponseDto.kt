package com.inno.impl.data.models

import com.google.gson.annotations.SerializedName

data class GuideResponseDto(
    @SerializedName("topics")
    val topics: List<TopicDto>
)