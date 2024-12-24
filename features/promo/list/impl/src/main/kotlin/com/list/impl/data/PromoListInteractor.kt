package com.list.impl.data

import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.list.impl.data.mappers.PromoListMapper
import com.list.impl.data.models.local.PromoCodesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PromoListInteractor @Inject constructor(
    private val api: PromoListApi,
    private val listMapper: PromoListMapper
) {
    suspend fun getPromoCodes(): ResponseState<PromoCodesResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = api.promoCodes()
                ResponseState.Success(listMapper.map(dto))
            } catch (exception: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}
