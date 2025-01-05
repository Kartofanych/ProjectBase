package com.example.travelling.login.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.example.travelling.login.LoginFeatureEntry
import com.example.travelling.login.impl.LoginFeatureImpl
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