package com.favourites.api.domain

import com.example.travelling.common.data.models.local.ResponseState

interface LikeInteractor {

    suspend fun changeFavorite(id: String, isLiked: Boolean): ResponseState<Unit>
}