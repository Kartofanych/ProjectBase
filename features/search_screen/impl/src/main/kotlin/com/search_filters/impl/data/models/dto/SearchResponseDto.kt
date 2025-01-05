package com.search_filters.impl.data.models.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.ActivityDto
import com.google.gson.annotations.SerializedName

@Keep
class SearchResponseDto(

    @SerializedName("cursor")
    val cursor: String? = null,

    @SerializedName("result")
    val items: List<ActivityDto>
)
