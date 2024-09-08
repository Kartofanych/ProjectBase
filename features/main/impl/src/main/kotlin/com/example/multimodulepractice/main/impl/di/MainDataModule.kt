package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.main.impl.data.mappers.ColorMapperImpl
import com.main.common.domain.ColorMapper
import dagger.Binds
import dagger.Module

@Module
interface MainDataModule {

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper
}