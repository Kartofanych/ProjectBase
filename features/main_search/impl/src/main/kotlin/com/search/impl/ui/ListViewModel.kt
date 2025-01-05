package com.search.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.common.data.models.local.ResponseState
import com.main.common.di.MainScope
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
        _uiStateFlow.update { ListUiState.Loading }
        viewModelScope.launch {
            when (val result = listInteractor.getRecommendations()) {
                is ResponseState.Error -> {
                    _uiStateFlow.update { ListUiState.Error }
                }

                is ResponseState.Success -> {
                    _uiStateFlow.update {
                        ListUiState.Content(
                            result.data.hint,
                            result.data.attractions,
                            result.data.activityGroups
                        )
                    }
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

            is ListAction.OpenActivity -> {
                viewModelScope.launch {
                    _uiEvent.send(ListEvent.OpenService(action.id))
                }
            }

            ListAction.ReloadList -> getRecommendations()
        }
    }
}