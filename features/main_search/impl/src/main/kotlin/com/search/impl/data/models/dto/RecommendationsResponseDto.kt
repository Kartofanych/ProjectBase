package com.search.impl.data.models.dto

import com.google.gson.annotations.SerializedName

data class RecommendationsResponseDto(

    @SerializedName("hint")
    val hint: String,

    @SerializedName("attractions")
    val attractions: List<AttractionDto>,

    @SerializedName("activity_groups")
    val activityGroups: List<ActivityGroupDto>,
)