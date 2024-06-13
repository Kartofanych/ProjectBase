package com.example.multimodulepractice.di

import com.example.impl.di.modules.AuthProviderModule
import com.example.multimodulepractice.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [
        AppModule::class,
        AuthProviderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }

}