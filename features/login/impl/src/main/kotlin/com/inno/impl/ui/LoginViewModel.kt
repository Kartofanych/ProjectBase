package com.inno.impl.ui

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.AuthInfoManager
import com.example.api.models.AuthInfo
import com.example.common.di.AppContext
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthResult
import com.yandex.authsdk.YandexAuthSdk
import com.yandex.authsdk.YandexAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @AppContext
    private val context: Context,
    private val authInfoManager: AuthInfoManager
) : ViewModel() {

    private lateinit var events: LoginScreenEvents

    private var isAuthOpen = false

    fun setEvents(events: LoginScreenEvents) {
        this.events = events
    }

    @Composable
    fun HandleProgress(progress: Float) {
        if (progress == 1f && !isAuthOpen) {
            StartLogin()
        }
    }

    @Composable
    fun StartLogin() {
        val sdk = YandexAuthSdk.create(YandexAuthOptions(context))
        val launcher = rememberLauncherForActivityResult(sdk.contract) { result ->
            handleResult(result)
        }

        val loginOptions = YandexAuthLoginOptions()
        SideEffect {
            launcher.launch(loginOptions)
        }
        isAuthOpen = true
    }

    private fun handleResult(result: YandexAuthResult) {
        when (result) {
            is YandexAuthResult.Success -> onSuccessAuth(result.token)
            is YandexAuthResult.Failure -> onProcessError()
            YandexAuthResult.Cancelled -> onCancelled()
        }
    }

    private fun onCancelled() {
        isAuthOpen = false
        events.loginCancelled()
    }

    private fun onProcessError() {
        isAuthOpen = false
        events.loginCancelled()
    }

    private fun onSuccessAuth(token: YandexAuthToken) {
        viewModelScope.launch {
            authInfoManager.updateAuthInfo(AuthInfo(token.value))
            events.loginSuccess()
        }
    }

}