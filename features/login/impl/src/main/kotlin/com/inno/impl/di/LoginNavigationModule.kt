package com.inno.impl.di

import com.inno.api.LoginFeatureApi
import com.inno.impl.LoginFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LoginNavigationModule {

    @Provides
    fun loginFeature(): LoginFeatureApi {
        return LoginFeatureImpl()
    }

}