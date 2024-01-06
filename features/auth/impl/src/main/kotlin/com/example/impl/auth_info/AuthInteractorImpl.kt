package com.example.impl.auth_info

import com.example.api.AuthInfoManager
import com.example.api.AuthInteractor
import com.example.api.models.AuthInfo
import com.example.common.di.AppScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@AppScope
class AuthInteractorImpl @Inject constructor(
    private val authInfoManager: AuthInfoManager
) : AuthInteractor {

    override suspend fun updateAuthInfo(info: AuthInfo) {
        authInfoManager.updateAuthInfo(info)
    }

    override fun authInfo(): AuthInfo = authInfoManager.authInfo()

    override fun authInfoFlow(): Flow<AuthInfo> = authInfoManager.authInfoFlow()
}
