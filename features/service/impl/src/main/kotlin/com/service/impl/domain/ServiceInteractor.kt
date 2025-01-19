package com.service.impl.domain

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.service.impl.data.ServiceApi
import com.service.impl.data.mappers.ServiceMapper
import com.service.impl.data.models.local.Service
import com.service.impl.data.models.network.ServiceRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServiceInteractor @Inject constructor(
    private val api: ServiceApi,
    private val mapper: ServiceMapper
) {

    suspend fun service(serviceId: String): ResponseState<Service> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = api.service(ServiceRequest(serviceId))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "service", millis = time)
                    ResponseState.Success(mapper.map(response))
                }, catch = { throwable ->
                    Analytics.reportNetworkError(route = "service", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}
