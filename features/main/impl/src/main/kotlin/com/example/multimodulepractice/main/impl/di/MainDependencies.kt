package com.example.multimodulepractice.main.impl.di

import android.content.Context
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.domain.MapScreenRepository
import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import com.main.common.data.MainApi
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import javax.inject.Inject

class MainDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val geoRepository: GeoRepository,
    val mainApi: MainApi,
    val scope: CoroutineScope,
    val authInfoManager: AuthInfoManager,
    val categoryMapper: CategoryMapper,
    val mapScreenRepository: MapScreenRepository,
    val retrofit: Retrofit,
    val likeInteractor: LikeInteractor,
    val favoritesRepository: FavoritesRepository,
)
