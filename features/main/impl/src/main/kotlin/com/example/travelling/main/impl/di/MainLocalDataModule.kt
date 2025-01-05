package com.example.travelling.main.impl.di

import com.example.travelling.common.domain.ColorMapper
import com.example.travelling.main.impl.data.mappers.ColorMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MainLocalDataModule {

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper
}