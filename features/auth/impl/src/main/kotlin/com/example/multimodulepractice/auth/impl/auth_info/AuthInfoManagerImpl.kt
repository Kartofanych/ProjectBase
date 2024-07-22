package com.example.multimodulepractice.auth.impl.auth_info

import android.content.Context
import androidx.datastore.dataStore
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.auth.impl.auth_info.mappers.toDto
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.auth.impl.auth_info.mappers.toModel
import com.example.multimodulepractice.auth.impl.auth_info.models.AuthInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AppScope
class AuthInfoManagerImpl @Inject constructor(
    @AppContext
    context: Context
) : AuthInfoManager {

    private val dataStore = context.dataStore

    override suspend fun updateAuthInfo(info: AuthInfo) {
        dataStore.updateData {
            info.toDto()
        }
    }

    override fun authInfo(): AuthInfo = runBlocking {
        dataStore.data.first().toModel()
    }

    override fun authInfoFlow(): Flow<AuthInfo> =
        dataStore.data.map(AuthInfoDto::toModel)

}

val Context.dataStore by dataStore("auth-info.json", AuthInfoSerializer())
