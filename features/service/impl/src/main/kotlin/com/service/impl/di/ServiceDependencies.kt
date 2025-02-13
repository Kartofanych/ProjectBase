package com.service.impl.di

import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.common.domain.ColorMapper
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.geo.repository.GeoRepository
import com.reviews.api.SendReviewInteractor
import com.service.impl.data.ServiceApi
import javax.inject.Inject

class ServiceDependencies @Inject constructor(
    val serviceApi: ServiceApi,
    val colorMapper: ColorMapper,
    val deeplinkHandler: DeeplinkHandler,
    val authInfoManager: AuthInfoManager,
    val sendReviewInteractor: SendReviewInteractor,
    val geoRepository: GeoRepository,
)