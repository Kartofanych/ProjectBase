package com.example.multimodulepractice.main.impl.data.local_models.list

data class RecommendationsResponse(
    val recommendList: List<Attraction>,
    val closeList: List<Attraction>
)