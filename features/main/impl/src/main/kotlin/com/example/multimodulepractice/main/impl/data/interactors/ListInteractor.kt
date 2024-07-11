package com.example.multimodulepractice.main.impl.data.interactors

import com.example.multimodulepractice.common.models.local.GeoPoint.Companion.toDto
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.data.local_models.list.RecommendationsResponse
import com.example.multimodulepractice.main.impl.data.mappers.ListMapper
import com.example.multimodulepractice.main.impl.data.network.MainApi
import com.example.multimodulepractice.main.impl.data.network.models.request.StartInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListInteractor @Inject constructor(
    private val geoRepository: GeoRepository,
    private val api: MainApi,
    private val listMapper: ListMapper
) {

    suspend fun getRecommendations(): ResponseState<RecommendationsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val geoPoint = geoRepository.geoInfo().currentPoint.toDto()
                val response = api.getRecommendations(
                    StartInfoRequest(geoPoint = geoPoint)
                )
                return@withContext ResponseState.Success(data = listMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error()
            }
        }
    }


}