package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RecommendationsResponseDto(
    @SerializedName("popular_items")
    val popularList: List<AttractionDto>,

    @SerializedName("close_items")
    val closeList: List<AttractionDto>
)