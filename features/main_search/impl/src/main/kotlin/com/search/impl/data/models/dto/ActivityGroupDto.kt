package com.search.impl.data.models.dto

import com.google.gson.annotations.SerializedName

data class ActivityGroupDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("activities")
    val activities: List<ActivityDto>
)
