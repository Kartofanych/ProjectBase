package com.example.multimodulepractice.main.impl.data.local_models.list

data class RecommendationsResponse(
    val popularList: List<VerticalAttraction>,
    val closeList: List<CloseAttraction>
)