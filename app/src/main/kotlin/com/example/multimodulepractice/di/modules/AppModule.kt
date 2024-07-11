package com.example.multimodulepractice.di.modules

import android.app.Application
import android.content.Context
import com.example.multimodulepractice.common.di.AppContext
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
class AppModule {

    @Provides
    @AppContext
    fun provideContext(app: Application): Context {
        return app.baseContext
    }

    @Provides
    fun provideScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

}