package com.search.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.common.di.MainScope
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.main.common.domain.RecommendedAttractionsRepository
import com.search.impl.domain.ListInteractor
import dagger.Reusable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
@Reusable
class ListViewModel @Inject constructor(
    private val listInteractor: ListInteractor,
    private val recommendedAttractionsRepository: RecommendedAttractionsRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiStateFlow: StateFlow<ListUiState>
        get() = _uiStateFlow

    private val _uiEvent = Channel<ListEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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
                viewModelScope.launch {
                    _uiEvent.send(ListEvent.OpenAttraction(action.id))
                }
            }

            ListAction.OpenSearch -> {
                viewModelScope.launch {
                    _uiEvent.send(ListEvent.OpenSearch)
                }
            }
        }
    }
}