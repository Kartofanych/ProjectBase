package com.search.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActivityGroupDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("activities")
    val activities: List<ActivityDto>
)
