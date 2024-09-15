package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.data.mappers.CategoryMapper
import com.example.multimodulepractice.main.impl.data.mappers.ColorMapperImpl
import com.example.multimodulepractice.common.domain.ColorMapper
import com.example.multimodulepractice.main.impl.data.mappers.CategoryMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface MainDataModule {

    @Binds
    fun colorMapper(colorMapperImpl: ColorMapperImpl): ColorMapper

    @Binds
    fun categoryMapper(categoryMapperImpl: CategoryMapperImpl): CategoryMapper
}