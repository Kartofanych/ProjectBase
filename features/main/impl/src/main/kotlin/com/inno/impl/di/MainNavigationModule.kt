package com.inno.impl.di

import com.inno.api.MainFeatureApi
import com.inno.impl.MainFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainNavigationModule {

    @Provides
    fun mainNavigation(): MainFeatureApi {
        return MainFeatureImpl()
    }
}