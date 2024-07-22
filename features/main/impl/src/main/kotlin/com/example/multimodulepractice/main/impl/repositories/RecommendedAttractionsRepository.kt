package com.example.multimodulepractice.main.impl.repositories

import android.util.Log
import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction
import com.example.multimodulepractice.main.impl.di.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@MainScope
class RecommendedAttractionsRepository @Inject constructor() {

    private val _recommendedAttractions = MutableStateFlow<List<Attraction>>(emptyList())
    val recommendedAttractions = _recommendedAttractions.asStateFlow()
    init {
        Log.d("121212", "init"+this.toString())
    }

    suspend fun updateAttractions(recommendList: List<Attraction>) {
        Log.d("121212", "update")
        _recommendedAttractions.emit(recommendList)
    }
}