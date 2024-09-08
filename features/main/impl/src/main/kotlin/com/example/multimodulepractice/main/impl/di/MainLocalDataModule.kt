package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.main.impl.data.mappers.ColorMapperImpl
import com.example.multimodulepractice.main.impl.data.repositories.RecommendedAttractionsRepositoryImpl
import com.main.common.domain.RecommendedAttractionsRepository
import com.example.multimodulepractice.main.impl.data.mappers.LandmarkMapperImpl
import com.main.common.domain.ColorMapper
import com.main.common.domain.LandmarkMapper
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MainLocalDataModule {

    @Binds
    @MainScope
    @Reusable
    fun recommendedAttractionsRepository(recommendedAttractionsRepositoryImpl: RecommendedAttractionsRepositoryImpl): RecommendedAttractionsRepository

    @Binds
    fun landmarkMapper(landmarkMapperImpl: LandmarkMapperImpl): LandmarkMapper

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper
}