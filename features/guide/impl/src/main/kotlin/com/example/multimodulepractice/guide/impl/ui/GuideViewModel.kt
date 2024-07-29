package com.example.multimodulepractice.guide.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.example.multimodulepractice.guide.impl.di.GuideScope
import com.example.multimodulepractice.guide.impl.interactors.GuideInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@GuideScope
class GuideViewModel @Inject constructor(
    private val guideInteractor: GuideInteractor,
    id: String
) : ViewModel() {

    private val _uiEvent = Channel<GuideUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<GuideUiState>(GuideUiState.Loading)
    val uiStateFlow: StateFlow<GuideUiState>
        get() = _uiStateFlow

    private var launchJob: Job? = null

    init {
        launch(id)
    }

    private fun launch(landmarkId: String) {
        _uiStateFlow.update { GuideUiState.Loading }
        launchJob?.cancel()
        launchJob = viewModelScope.launch {
            when (val result = runWithMinTime({ guideInteractor.guide(landmarkId) }, 2000L)) {
                is ResponseState.Error -> {
                    _uiStateFlow.update { GuideUiState.Error }
                }

                is ResponseState.Success -> {
                    _uiStateFlow.update {
                        GuideUiState.Content(
                            currentPage = 0,
                            topics = result.data.topics
                        )
                    }
                }
            }
        }
    }

    fun onGuideAction(action: GuideAction) {
        when (action) {
            GuideAction.OnBackPressed -> {
                viewModelScope.launch {
                    _uiEvent.send(GuideUiEvent.OnBackClicked)
                }
            }

            GuideAction.OnNextPage -> {
                viewModelScope.launch {
                    val currentValue = uiStateFlow.value as GuideUiState.Content
                    _uiStateFlow.emit(
                        currentValue.copy(
                            currentPage = minOf(
                                currentValue.topics.size - 1, currentValue.currentPage + 1
                            )
                        )
                    )
                }
            }

            GuideAction.OnPreviousPage -> {
                viewModelScope.launch {
                    val currentValue = uiStateFlow.value as GuideUiState.Content
                    _uiStateFlow.emit(
                        currentValue.copy(
                            currentPage = maxOf(
                                0, currentValue.currentPage - 1
                            )
                        )
                    )
                }
            }
        }
    }

}