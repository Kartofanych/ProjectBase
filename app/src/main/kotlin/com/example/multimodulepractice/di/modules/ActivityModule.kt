package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.common.data.DeeplinkHandlerImpl
import com.example.multimodulepractice.common.di.ActivityScope
import com.example.multimodulepractice.common.domain.DeeplinkHandler
import dagger.Binds
import dagger.Module

@Module
interface ActivityModule {

    @Binds
    @ActivityScope
    fun deeplinkHandler(impl: DeeplinkHandlerImpl): DeeplinkHandler
}