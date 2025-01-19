package com.promo.impl.ui

import androidx.lifecycle.ViewModel
import com.example.travelling.common.utils.Analytics
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class PromoViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<PromoEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(PromoUiState())
    val uiStateFlow: StateFlow<PromoUiState>
        get() = _uiStateFlow

    init {
        Analytics.reportOpenFeature("promo")
    }

    fun onAction(promoAction: PromoAction) {
        when (promoAction) {
            else -> Unit
        }
    }
}
