package com.favourites.impl.data.interactors

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.favourites.impl.data.FavoritesApi
import com.favourites.impl.data.mappers.FavoritesMapper
import com.favourites.impl.data.models.local.FavoritesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class FavouritesInteractor @Inject constructor(
    private val api: FavoritesApi,
    private val mapper: FavoritesMapper,
) {

    suspend fun favorite(): ResponseState<FavoritesResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = api.favorites()
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "favorites", millis = time)
                    return@withContext ResponseState.Success(mapper.map(response))
                }, catch = { throwable ->
                    Analytics.reportNetworkError(route = "favorites", throwable = throwable)
                    when {
                        throwable is HttpException && throwable.code() == 401 -> ResponseState.Error.Unauthorized()
                        else -> ResponseState.Error.Default()
                    }
                }
            )
        }
    }
}