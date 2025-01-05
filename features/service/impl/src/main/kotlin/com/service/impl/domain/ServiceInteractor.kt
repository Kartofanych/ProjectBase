package com.service.impl.domain

import com.example.travelling.common.data.models.local.ResponseState
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
            try {
                val response = api.service(ServiceRequest(serviceId))
                ResponseState.Success(mapper.map(response))
            } catch (e: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}
