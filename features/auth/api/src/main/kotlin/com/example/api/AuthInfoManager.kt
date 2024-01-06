package com.example.api

import com.example.api.models.AuthInfo
import kotlinx.coroutines.flow.Flow

interface AuthInfoManager {
    suspend fun updateAuthInfo(info: AuthInfo)
    fun authInfo(): AuthInfo
    fun authInfoFlow(): Flow<AuthInfo>
}