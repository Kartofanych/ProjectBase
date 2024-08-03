package com.travelling.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.travelling.api.AppConfig
import com.travelling.impl.AppConfigImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface AppConfigModule {

    @Binds
    @AppScope
    @Reusable
    fun appConfig(appConfigImpl: AppConfigImpl): AppConfig

}