package com.favourites.impl.data.interactors

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.favourites.api.domain.LikeInteractor
import com.favourites.impl.data.LikeApi
import com.favourites.impl.data.models.dto.LikeRequestDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeInteractorImpl @Inject constructor(
    private val likeApi: LikeApi
) : LikeInteractor {

    override suspend fun changeFavorite(id: String, isLiked: Boolean): ResponseState<Unit> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    likeApi.like(LikeRequestDto(id, isLiked))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "like", millis = time)
                    ResponseState.Success(Unit)
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "like", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}