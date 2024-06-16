package com.example.impl.di

import com.example.api.AuthInfoManager
import com.example.api.AuthInteractor
import com.example.common.di.AppScope
import com.example.impl.auth_info.AuthInfoManagerImpl
import com.example.impl.auth_info.AuthInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthProviderModule {

    @Binds
    @AppScope
    fun authManager(authInfoManagerImpl: AuthInfoManagerImpl): AuthInfoManager

    @Binds
    @AppScope
    fun authInteractor(authInteractorImpl: AuthInteractorImpl): AuthInteractor

}