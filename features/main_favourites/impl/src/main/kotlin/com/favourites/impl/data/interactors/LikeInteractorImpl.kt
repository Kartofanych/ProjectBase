package com.favourites.impl.data.interactors

import com.example.multimodulepractice.common.data.models.local.ResponseState
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
            try {
                likeApi.like(LikeRequestDto(id, isLiked))
                ResponseState.Success(Unit)
            } catch (_: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}