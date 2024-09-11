package com.search.impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.common.di.MainScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.main.common.domain.AttractionRepository
import com.main.common.domain.RecommendedAttractionsRepository
import com.search.impl.domain.ListInteractor
import dagger.Reusable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
@Reusable
class ListViewModel @Inject constructor(
    private val listInteractor: ListInteractor,
    private val attractionRepository: AttractionRepository,
    private val recommendedAttractionsRepository: RecommendedAttractionsRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiStateFlow: StateFlow<ListUiState>
        get() = _uiStateFlow

    init {
        Log.d("121212", "init")
        getRecommendations()
    }

    private fun getRecommendations() {
        viewModelScope.launch {
            when (val result = listInteractor.getRecommendations()) {
                is ResponseState.Error -> {
                    _uiStateFlow.update { ListUiState.Error }
                }

                is ResponseState.Success -> {
                    _uiStateFlow.update {
                        ListUiState.Content(
                            result.data.recommendList,
                            result.data.closeList
                        )
                    }
                    recommendedAttractionsRepository.updateAttractions(result.data.recommendList)
                }
            }
        }
    }

    fun onListAction(action: ListAction) {
        when (action) {
            is ListAction.OpenAttraction -> {
                attractionRepository.getLandmark(action.id)
            }
        }
    }
}