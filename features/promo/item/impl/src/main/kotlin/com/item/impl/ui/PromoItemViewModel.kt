package com.item.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.item.impl.data.PromoItemInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoItemViewModel @Inject constructor(
    private val token: String,
    private val promoItemInteractor: PromoItemInteractor
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<PromoItemEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiStateFlow = MutableStateFlow<PromoItemUiState>(PromoItemUiState.Loading)
    val uiStateFlow: StateFlow<PromoItemUiState>
        get() = _uiStateFlow

    init {
        getPromo()
    }

    private fun getPromo() {
        _uiStateFlow.update { PromoItemUiState.Loading }
        viewModelScope.launch {
            when (val result = promoItemInteractor.getPromo(token)) {
                is ResponseState.Error -> _uiStateFlow.update { PromoItemUiState.Error }
                is ResponseState.Success -> _uiStateFlow.update { PromoItemUiState.Content(result.data) }
            }
        }
    }

    fun onAction(action: PromoItemAction) {
        when (action) {
            PromoItemAction.Close -> _uiEvent.tryEmit(PromoItemEvent.OnBack)

            PromoItemUiState.OnReload -> getPromo()

            is PromoItemAction.OpenInfo -> {
                _uiEvent.tryEmit(PromoItemEvent.OnOpenInfo(action.id, action.type))
            }
        }
    }
}
