package com.search.impl.domain


import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.search.impl.data.MainListApi
import com.search.impl.data.mappers.ListMapper
import com.search.impl.data.models.local.ListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListInteractor @Inject constructor(
    private val api: MainListApi,
    private val listMapper: ListMapper
) {

    suspend fun getRecommendations(): ResponseState<ListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getListPage()
                return@withContext ResponseState.Success(data = listMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error.Default()
            }
        }
    }
}