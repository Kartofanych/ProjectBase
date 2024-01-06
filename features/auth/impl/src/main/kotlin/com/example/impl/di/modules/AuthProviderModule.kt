package com.example.impl.di.modules

import com.example.api.AuthInfoManager
import com.example.api.AuthInteractor
import com.example.common.di.AppScope
import com.example.impl.auth_info.AuthInfoManagerImpl
import com.example.impl.auth_info.AuthInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface AuthProviderModule {

    @Binds
    @AppScope
    fun authManager(authInfoManagerImpl: AuthInfoManagerImpl): AuthInfoManager

    @Binds
    @AppScope
    fun authInteractor(authInteractorImpl: AuthInteractorImpl): AuthInteractor

}