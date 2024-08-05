package com.example.multimodulepractice.main.impl.repositories

import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction
import com.example.multimodulepractice.common.di.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@MainScope
class RecommendedAttractionsRepository @Inject constructor() {

    private val _recommendedAttractions = MutableStateFlow<List<Attraction>>(emptyList())
    val recommendedAttractions = _recommendedAttractions.asStateFlow()

    suspend fun updateAttractions(recommendList: List<Attraction>) {
        _recommendedAttractions.emit(recommendList)
    }
}