package com.item.impl.data

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
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
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val dto = api.promoCodeInfo(PromoItemRequest(token))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "promocode_info", millis = time)
                    ResponseState.Success(itemMapper.map(dto))
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "promocode_info", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}
