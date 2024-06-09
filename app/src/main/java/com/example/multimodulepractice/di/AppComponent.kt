package com.example.multimodulepractice.di

import com.example.impl.di.modules.AuthProviderModule
import com.example.multimodulepractice.di.modules.AppModule
import com.example.multimodulepractice.di.modules.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [
        AppModule::class,
        NavigationModule::class,
        AuthProviderModule::class
    ]
)
interface AppComponent : AppProvider {

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }

}