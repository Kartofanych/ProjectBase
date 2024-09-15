package com.search.impl.data

import com.search.impl.data.models.dto.SearchRequest
import com.search.impl.data.models.dto.SearchResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchApi {

    @POST("search")
    suspend fun search(@Body searchRequest: SearchRequest): SearchResponseDto
}