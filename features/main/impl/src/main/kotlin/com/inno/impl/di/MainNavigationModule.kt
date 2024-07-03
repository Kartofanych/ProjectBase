package com.inno.impl.di

import com.inno.api.MainFeatureEntry
import com.inno.impl.MainFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainNavigationModule {

    @Binds
    fun mainNavigation(mainFeatureImpl: MainFeatureImpl): MainFeatureEntry
}