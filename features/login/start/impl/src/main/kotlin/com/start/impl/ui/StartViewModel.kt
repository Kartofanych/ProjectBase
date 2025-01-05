package com.start.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.auth.models.AuthInfo
import com.start.impl.di.StartScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@StartScope
class StartViewModel @Inject constructor(
    private val authInfoManager: AuthInfoManager
) : ViewModel() {

    private val _uiEvent = Channel<StartEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(StartUiState())
    val uiStateFlow: StateFlow<StartUiState>
        get() = _uiStateFlow

    fun onAction(action: StartAction) {
        when (action) {
            StartAction.OnGuestLogin -> {
                viewModelScope.launch {
                    authInfoManager.updateAuthInfo(AuthInfo.Guest)
                    _uiEvent.send(StartEvent.OnGuestLogin)
                }
            }

            StartAction.OnLogin -> {
                viewModelScope.launch { _uiEvent.send(StartEvent.OnLogin) }
            }
        }
    }
}
