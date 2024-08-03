package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.auth.impl.di.AuthProviderModule
import com.example.multimodulepractice.geo.di.GeoProviderModule
import com.travelling.impl.di.AppConfigModule
import dagger.Module

@Module(
    includes = [
        AppConfigModule::class,
        AuthProviderModule::class,
        GeoProviderModule::class,
    ]
)
interface DataModule