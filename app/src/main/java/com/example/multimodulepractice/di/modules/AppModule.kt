package com.example.multimodulepractice.di.modules

import android.content.Context
import com.example.common.di.AppScope
import dagger.Module
import dagger.Provides

@AppScope
@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}