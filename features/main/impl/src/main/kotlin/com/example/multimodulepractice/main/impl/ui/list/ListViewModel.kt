package com.example.multimodulepractice.main.impl.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.main.impl.data.interactors.ListInteractor
import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.repositories.RecommendedAttractionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class ListViewModel @Inject constructor(
    private val listInteractor: ListInteractor,
    private val attractionRepository: AttractionRepository,
    private val recommendedAttractionsRepository: RecommendedAttractionsRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiStateFlow: StateFlow<ListUiState>
        get() = _uiStateFlow

    init {
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