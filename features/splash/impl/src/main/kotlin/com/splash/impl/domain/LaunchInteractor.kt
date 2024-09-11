package com.splash.impl.domain

import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.splash.impl.BuildConfig
import com.splash.impl.data.LaunchApi
import com.splash.impl.data.mappers.LaunchMapper
import com.splash.impl.data.models.dto.LaunchRequest
import com.splash.impl.data.models.local.LaunchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
    private val launchApi: LaunchApi,
    private val launchMapper: LaunchMapper
) {

    suspend fun launch(): ResponseState<LaunchResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = launchApi.launch(LaunchRequest(BuildConfig.VERSION_NAME))
                ResponseState.Success(launchMapper.map(response))
            } catch (exception: Exception) {
                when {
                    exception is HttpException && exception.code() == 426 -> ResponseState.Error.OldVersion()
                    else -> ResponseState.Error.Default()
                }
            }
        }
    }
}

