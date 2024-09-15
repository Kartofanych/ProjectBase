package com.example.multimodulepractice.main.impl.data.repositories

import com.main.common.di.MainScope
import com.main.common.data.local.Attraction
import com.main.common.domain.RecommendedAttractionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@MainScope
class RecommendedAttractionsRepositoryImpl @Inject constructor() :
    RecommendedAttractionsRepository {

    private val _recommendedAttractions =
        MutableStateFlow<List<Attraction>>(emptyList())
    override val recommendedAttractions = _recommendedAttractions.asStateFlow()

    override suspend fun updateAttractions(recommendList: List<Attraction>) {
        _recommendedAttractions.emit(recommendList)
    }
}