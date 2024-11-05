package com.example.multimodulepractice.login.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.example.multimodulepractice.login.LoginFeatureEntry
import com.example.multimodulepractice.login.impl.LoginFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginNavigationModule {
    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(LoginFeatureEntry::class)
    fun loginFeature(loginFeatureImpl: LoginFeatureImpl): FeatureEntry
}