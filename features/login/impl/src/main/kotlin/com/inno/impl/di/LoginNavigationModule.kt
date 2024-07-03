package com.inno.impl.di

import com.inno.api.LoginFeatureEntry
import com.inno.impl.LoginFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginNavigationModule {

    @Binds
    fun loginFeature(loginFeatureImpl: LoginFeatureImpl): LoginFeatureEntry

}