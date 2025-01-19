package com.list.impl.data

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
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
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val dto = api.promoCodes()
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "promocodes", millis = time)
                    ResponseState.Success(listMapper.map(dto))
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "promocodes", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}
