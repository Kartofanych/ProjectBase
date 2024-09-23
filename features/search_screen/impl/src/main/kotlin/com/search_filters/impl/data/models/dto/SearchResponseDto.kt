package com.search_filters.impl.data.models.dto

import com.example.multimodulepractice.common.data.models.network.ActivityDto
import com.google.gson.annotations.SerializedName

class SearchResponseDto(

    @SerializedName("cursor")
    val cursor: String? = null,

    @SerializedName("result")
    val items: List<ActivityDto>
)
