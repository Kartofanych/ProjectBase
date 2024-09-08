package com.main.common.domain

import com.main.common.data.local.Attraction
import kotlinx.coroutines.flow.StateFlow

interface RecommendedAttractionsRepository {

    val recommendedAttractions: StateFlow<List<Attraction>>

    suspend fun updateAttractions(recommendList: List<Attraction>)
}