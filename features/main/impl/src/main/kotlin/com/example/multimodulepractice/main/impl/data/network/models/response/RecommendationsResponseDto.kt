package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RecommendationsResponseDto(
    @SerializedName("popular_items")
    val popularList: List<VerticalAttractionDto>,

    @SerializedName("close_items")
    val closeList: List<CloseAttractionDto>
)