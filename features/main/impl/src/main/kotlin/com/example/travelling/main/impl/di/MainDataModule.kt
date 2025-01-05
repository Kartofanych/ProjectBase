package com.example.travelling.main.impl.di

import com.example.travelling.common.data.mappers.CategoryMapper
import com.example.travelling.common.domain.ColorMapper
import com.example.travelling.main.impl.data.mappers.CategoryMapperImpl
import com.example.travelling.main.impl.data.mappers.ColorMapperImpl
import com.example.travelling.main.impl.data.repositories.MapScreenRepositoryImpl
import com.example.travelling.main.impl.domain.MapScreenRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MainDataModule {

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper

    @Binds
    fun categoryMapper(categoryMapperImpl: CategoryMapperImpl): CategoryMapper

    @Binds
    @Reusable
    fun mapRepository(mapScreenRepositoryImpl: MapScreenRepositoryImpl): MapScreenRepository
}