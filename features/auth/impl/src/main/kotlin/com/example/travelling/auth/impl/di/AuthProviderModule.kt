package com.example.travelling.auth.impl.di

import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.auth.impl.auth_info.AuthInfoManagerImpl
import com.example.travelling.common.di.AppScope
import dagger.Binds
import dagger.Module

@Module
interface AuthProviderModule {

    @Binds
    @AppScope
    fun authManager(authInfoManagerImpl: AuthInfoManagerImpl): AuthInfoManager

}