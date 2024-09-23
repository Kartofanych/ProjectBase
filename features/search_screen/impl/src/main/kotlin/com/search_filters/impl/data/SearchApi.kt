package com.search_filters.impl.data

import com.search_filters.impl.data.models.dto.SearchRequest
import com.search_filters.impl.data.models.dto.SearchResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchApi {

    @POST("search")
    suspend fun search(@Body searchRequest: SearchRequest): SearchResponseDto
}