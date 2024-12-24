package com.item.impl.data

import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.item.impl.data.mappers.PromoItemMapper
import com.item.impl.data.models.dto.PromoItemRequest
import com.item.impl.data.models.local.PromoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PromoItemInteractor @Inject constructor(
    private val api: PromoItemApi,
    private val itemMapper: PromoItemMapper
) {
    suspend fun getPromo(token: String): ResponseState<PromoItem> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = api.promoCodeInfo(PromoItemRequest(token))
                ResponseState.Success(itemMapper.map(dto))
            } catch (exception: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}
