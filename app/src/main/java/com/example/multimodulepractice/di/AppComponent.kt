package com.example.multimodulepractice.di

import android.content.Context
import com.example.impl.di.modules.AuthProviderModule
import com.example.multimodulepractice.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [
        NavigationModule::class,
        AuthProviderModule::class
    ]
)
interface AppComponent : AppProvider {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}