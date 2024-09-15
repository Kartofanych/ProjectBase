package com.example.multimodulepractice.login.impl.data.interactors

import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.example.multimodulepractice.login.impl.data.AuthApi
import com.example.multimodulepractice.login.impl.data.YandexAuthInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YandexAuthInfoInteractor @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun getInfo(token: String): ResponseState<YandexAuthInfo> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authApi.getInfo("OAuth $token")
                ResponseState.Success(
                    YandexAuthInfo(
                        response.name,
                        response.email,
                        "$DEFAULT_AVATAR_LINK${response.avatarId}$DEFAULT_AVATAR_SIZE"
                    )
                )
            } catch (err: Exception) {
                ResponseState.Error.Default()
            }
        }
    }

    private companion object {
        const val DEFAULT_AVATAR_LINK = "https://avatars.yandex.net/get-yapic/"
        const val DEFAULT_AVATAR_SIZE = "/islands-200"
    }

}