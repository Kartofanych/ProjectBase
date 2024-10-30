package com.service.impl.di

import com.example.multimodulepractice.common.domain.ColorMapper
import com.example.multimodulepractice.common.domain.DeeplinkHandler
import com.service.impl.data.ServiceApi
import javax.inject.Inject

class ServiceDependencies @Inject constructor(
    val serviceApi: ServiceApi,
    val colorMapper: ColorMapper,
    val deeplinkHandler: DeeplinkHandler,
)