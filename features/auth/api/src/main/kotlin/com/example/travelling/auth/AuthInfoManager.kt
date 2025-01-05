package com.example.travelling.auth

import com.example.travelling.auth.models.AuthInfo
import kotlinx.coroutines.flow.Flow

interface AuthInfoManager {
    suspend fun updateAuthInfo(info: AuthInfo)
    fun authInfo(): AuthInfo
    fun authInfoFlow(): Flow<AuthInfo>
    suspend fun isAuthorized(): Boolean
}