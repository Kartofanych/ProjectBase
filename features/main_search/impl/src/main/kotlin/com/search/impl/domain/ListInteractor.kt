package com.search.impl.domain

import com.example.multimodulepractice.common.models.local.GeoPoint.Companion.toDto
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.main.common.data.MainApi
import com.main.common.data.dto.GeoRequest
import com.search.impl.data.mappers.ListMapper
import com.search.impl.data.models.ListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListInteractor @Inject constructor(
    private val geoRepository: GeoRepository,
    private val api: MainApi,
    private val listMapper: ListMapper
) {

    suspend fun getRecommendations(): ResponseState<ListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val geoPoint = geoRepository.geoInfo().currentPoint.toDto()
                val response = api.getRecommendations(
                    GeoRequest(geoPoint = geoPoint)
                )
                return@withContext ResponseState.Success(data = listMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error.Default()
            }
        }
    }
}