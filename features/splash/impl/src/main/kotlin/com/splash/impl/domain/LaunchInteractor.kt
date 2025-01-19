package com.splash.impl.domain

import com.example.multimodulepractice.splash.impl.BuildConfig
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
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
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = launchApi.launch(LaunchRequest(BuildConfig.VERSION_NAME))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "launch", time)
                    ResponseState.Success(launchMapper.map(response))
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "launch", throwable = throwable)
                    when {
                        throwable is HttpException && throwable.code() == 426 -> ResponseState.Error.OldVersion()
                        else -> ResponseState.Error.Default()
                    }
                }
            )
        }
    }
}

