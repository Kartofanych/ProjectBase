package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.domain.ColorMapper
import com.example.multimodulepractice.main.impl.data.mappers.ColorMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MainLocalDataModule {

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper
}