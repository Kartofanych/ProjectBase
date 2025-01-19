package com.start.impl.ui

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.auth.models.AuthInfo
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.runWithMinTime
import com.start.impl.data.LoginInteractor
import com.start.impl.ui.ProcessUiState.LoginState
import com.start.impl.ui.ProcessUiState.LoginState.CodeState.CodeScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProcessViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val authInfoManager: AuthInfoManager,
) : ViewModel() {

    private val _uiEvent = Channel<ProcessEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(ProcessUiState.EMPTY)
    val uiStateFlow: StateFlow<ProcessUiState>
        get() = _uiStateFlow

    private var code: String = ""

    init {
        Analytics.reportOpenFeature("login_process")
    }

    fun onAction(action: ProcessAction) {
        when (action) {
            is ProcessAction.ChangeMailText -> {
                _uiStateFlow.update { it.copy(currentText = action.text) }
            }

            ProcessAction.NextPressed -> {
                if (mailIsValid()) {
                    _uiStateFlow.update {
                        it.copy(loginState = LoginState.CodeState())
                    }
                    register()
                } else {
                    _uiStateFlow.update {
                        it.copy(loginState = LoginState.EmailState("Email is invalid"))
                    }
                }
            }

            ProcessAction.OnClose -> {
                viewModelScope.launch {
                    _uiEvent.send(ProcessEvent.BackEvent)
                }
            }

            is ProcessAction.ChangeCodeText -> {
                (_uiStateFlow.value.loginState as? LoginState.CodeState)?.let { state ->
                    if (state.state == CodeScreenState.ERROR) {
                        _uiStateFlow.update { it.copy(loginState = state.copy(state = CodeScreenState.DEFAULT)) }
                    }
                    code = action.code
                    validateCode()
                }
            }

            is ProcessAction.CodeTextTapped -> {
                (_uiStateFlow.value.loginState as? LoginState.CodeState)?.let { state ->
                    _uiStateFlow.update { it.copy(loginState = state.copy(currentCode = action.index)) }
                }
            }

            ProcessAction.ResendCode -> register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            when (loginInteractor.register(_uiStateFlow.value.currentText)) {
                is ResponseState.Error -> {
                    delay(3000)
                    register()
                }

                is ResponseState.Success -> Unit
            }
        }
    }

    private fun validateCode() {
        if (_uiStateFlow.value.loginState !is LoginState.CodeState || code.length != 4) return
        val loginState = _uiStateFlow.value.loginState as LoginState.CodeState
        _uiStateFlow.update {
            it.copy(loginState = loginState.copy(state = CodeScreenState.LOADING))
        }

        viewModelScope.launch {
            val mail = _uiStateFlow.value.currentText
            when (val result = runWithMinTime({ loginInteractor.validateCode(mail, code) })) {
                is ResponseState.Error.BadCode -> {
                    Analytics.reportFeatureAction(feature = "login_process", action = "bad_code")
                    _uiStateFlow.update {
                        it.copy(loginState = loginState.copy(state = CodeScreenState.ERROR))
                    }
                }

                is ResponseState.Error -> {
                    delay(3000)
                    validateCode()
                }

                is ResponseState.Success -> {
                    authInfoManager.updateAuthInfo(
                        AuthInfo.User(
                            token = result.data.token,
                            image = result.data.icon,
                            mail = _uiStateFlow.value.currentText,
                            name = result.data.name,
                        )
                    )
                    Analytics.reportFeatureAction(feature = "login_process", action = "success")
                    _uiEvent.send(ProcessEvent.OpenMain)
                }
            }
        }
    }

    private fun mailIsValid(): Boolean {
        val text = uiStateFlow.value.currentText
        return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text)
            .matches()
    }
}
