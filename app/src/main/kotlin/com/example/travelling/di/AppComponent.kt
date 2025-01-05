package com.example.travelling.di

import android.app.Application
import com.example.travelling.AppProvider
import com.example.travelling.di.modules.ApiModule
import com.example.travelling.di.modules.AppModule
import com.example.travelling.di.modules.DataModule
import com.example.travelling.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [
        AppModule::class,
        ApiModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent : AppProvider {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }
}