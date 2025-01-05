package com.attraction.impl.di

import com.attraction.impl.data.AttractionApi
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.common.data.mappers.CategoryMapper
import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import javax.inject.Inject

class AttractionDependencies @Inject constructor(
    val categoryMapper: CategoryMapper,
    val attractionApi: AttractionApi,
    val likeInteractor: LikeInteractor,
    val favoritesRepository: FavoritesRepository,
    val authInfoManager: AuthInfoManager,
)