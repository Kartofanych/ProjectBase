package com.onboarding.impl.ui

import androidx.lifecycle.ViewModel
import com.travelling.api.UserPreferences
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<OnboardingEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiStateFlow = MutableStateFlow(OnboardingUiState())
    val uiStateFlow: StateFlow<OnboardingUiState>
        get() = _uiStateFlow

    fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.OnFinish -> {
                userPreferences.updateOnboardingShown()
                _uiEvent.tryEmit(OnboardingEvent.OpenLogin)
            }
        }
    }
}