package com.favourites.impl.data

import com.favourites.impl.data.models.dto.FavoritesResponseDto
import retrofit2.http.POST

interface FavoritesApi {

    @POST("favorites")
    suspend fun favorites(): FavoritesResponseDto
}