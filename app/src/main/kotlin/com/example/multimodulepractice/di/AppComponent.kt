package com.example.multimodulepractice.di

import android.app.Application
import com.example.multimodulepractice.AppProvider
import com.example.multimodulepractice.di.modules.ApiModule
import com.example.multimodulepractice.di.modules.AppModule
import com.example.multimodulepractice.di.modules.DataModule
import com.example.multimodulepractice.di.modules.NavigationModule
import com.example.multimodulepractice.di.modules.NetworkModule
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
        NavigationModule::class,
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