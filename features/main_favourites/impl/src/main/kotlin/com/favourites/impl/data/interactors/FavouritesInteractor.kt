package com.favourites.impl.data.interactors

import com.example.multimodulepractice.common.data.models.local.ResponseState
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
            try {
                val response = api.favorites()
                return@withContext ResponseState.Success(mapper.map(response))
            } catch (exception: Exception) {
                when {
                    exception is HttpException && exception.code() == 401 -> ResponseState.Error.Unauthorized()
                    else -> ResponseState.Error.Default()
                }
            }
        }
    }
}