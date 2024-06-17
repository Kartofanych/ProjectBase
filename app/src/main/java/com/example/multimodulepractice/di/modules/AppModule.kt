package com.example.multimodulepractice.di.modules

import android.app.Application
import android.content.Context
import com.example.common.di.AppContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
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