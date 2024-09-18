package com.search.impl.domain

import com.example.multimodulepractice.common.data.models.local.FilterDistance
import com.example.multimodulepractice.common.data.models.local.GeoPoint.Companion.toDto
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.search.impl.data.SearchApi
import com.search.impl.data.mappers.SearchMapper
import com.search.impl.data.models.dto.SearchFiltersDto
import com.search.impl.data.models.dto.SearchRequest
import com.search.impl.data.models.local.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val api: SearchApi,
    private val geoRepository: GeoRepository,
    private val searchMapper: SearchMapper
) {

    suspend fun search(searchText: String, cursor: String): ResponseState<SearchResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.search(
                    SearchRequest(
                        query = searchText,
                        geoPoint = geoRepository.geoInfoImmediately().currentPoint.toDto(),
                        cursor = cursor,
                        filters = SearchFiltersDto(
                            distance = FilterDistance.EVERYWHERE.km.toFloat(),
                            cities = listOf(
                                "cd23b201-cd53-467c-8d5a-641413a861ac",
                                "680e76ea-0dd5-4aba-8bb8-900473cad9d8"
                            ),
                            minRating = 0f
                        )
                    )
                )
                ResponseState.Success(searchMapper.map(response))
            } catch (exception: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}