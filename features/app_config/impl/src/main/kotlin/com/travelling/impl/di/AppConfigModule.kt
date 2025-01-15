package com.travelling.impl.di

import com.example.travelling.common.di.AppScope
import com.travelling.api.AppConfig
import com.travelling.api.UserPreferences
import com.travelling.impl.AppConfigImpl
import com.travelling.impl.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface AppConfigModule {

    @Binds
    @AppScope
    @Reusable
    fun appConfig(impl: AppConfigImpl): AppConfig


    @Binds
    @AppScope
    @Reusable
    fun userPreferencesConfig(impl: UserPreferencesImpl): UserPreferences
}