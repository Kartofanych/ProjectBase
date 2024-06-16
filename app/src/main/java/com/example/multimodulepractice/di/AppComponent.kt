package com.example.multimodulepractice.di

import android.app.Application
import com.example.impl.di.AuthProviderModule
import com.example.multimodulepractice.di.modules.ApiModule
import com.example.multimodulepractice.di.modules.AppModule
import com.example.multimodulepractice.di.modules.NetworkModule
import com.inno.geo.di.GeoProviderModule
import com.inno.impl.di.MainDependencies
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
        AuthProviderModule::class,
        GeoProviderModule::class
    ]
)
interface AppComponent {

    val mainDependencies: MainDependencies

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }

}