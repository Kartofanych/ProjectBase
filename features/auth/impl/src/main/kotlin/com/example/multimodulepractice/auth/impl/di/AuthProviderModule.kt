package com.example.multimodulepractice.auth.impl.di

import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.auth.impl.auth_info.AuthInfoManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface AuthProviderModule {

    @Binds
    @AppScope
    fun authManager(authInfoManagerImpl: AuthInfoManagerImpl): AuthInfoManager

}