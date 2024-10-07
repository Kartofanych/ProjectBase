package com.search.impl.data

import com.search.impl.data.models.dto.RecommendationsResponseDto
import retrofit2.http.POST

interface MainListApi {

    @POST("list")
    suspend fun getListPage(): RecommendationsResponseDto
}