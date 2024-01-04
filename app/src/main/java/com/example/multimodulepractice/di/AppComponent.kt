package com.example.multimodulepractice.di

import com.example.multimodulepractice.di.modules.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [NavigationModule::class]
)
interface AppComponent : AppProvider