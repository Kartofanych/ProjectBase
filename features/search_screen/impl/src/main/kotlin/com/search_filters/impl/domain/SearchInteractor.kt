package com.search_filters.impl.domain

import com.example.travelling.common.data.models.local.GeoPoint.Companion.toDto
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.geo.repository.GeoRepository
import com.search_filters.api.data.models.SearchFilters
import com.search_filters.impl.data.SearchApi
import com.search_filters.impl.data.mappers.SearchMapper
import com.search_filters.impl.data.models.dto.SearchFiltersDto
import com.search_filters.impl.data.models.dto.SearchRequest
import com.search_filters.impl.data.models.local.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val api: SearchApi,
    private val geoRepository: GeoRepository,
    private val searchMapper: SearchMapper
) {

    suspend fun search(
        searchText: String,
        filters: SearchFilters,
        cursor: String
    ): ResponseState<SearchResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.search(
                    SearchRequest(
                        query = searchText,
                        geoPoint = geoRepository.geoInfoImmediately().currentPoint.toDto(),
                        cursor = cursor,
                        filters = SearchFiltersDto(
                            distance = filters.distance.km.toFloat(),
                            cities = filters.cities.filter { it.isActive }.map { it.id },
                            minRating = filters.ratingFrom
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