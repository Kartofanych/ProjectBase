package com.service.impl.di

import com.service.impl.data.ServiceApi
import javax.inject.Inject

class ServiceDependencies @Inject constructor(
    val serviceApi: ServiceApi
)