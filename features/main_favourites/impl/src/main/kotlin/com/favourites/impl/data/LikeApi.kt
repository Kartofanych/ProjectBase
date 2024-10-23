package com.favourites.impl.data

import com.favourites.impl.data.models.dto.LikeRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LikeApi {

    @POST("like_attraction")
    suspend fun like(@Body body: LikeRequestDto)
}