package com.example.travelling.di.modules

import com.example.travelling.common.data.DeeplinkHandlerImpl
import com.example.travelling.common.di.ActivityScope
import com.example.travelling.common.domain.DeeplinkHandler
import dagger.Binds
import dagger.Module

@Module
interface ActivityModule {

    @Binds
    @ActivityScope
    fun deeplinkHandler(impl: DeeplinkHandlerImpl): DeeplinkHandler
}