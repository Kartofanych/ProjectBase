package com.example.multimodulepractice.di.modules

import com.inno.impl.di.GuideNavigationModule
import com.inno.impl.di.LoginNavigationModule
import com.inno.impl.di.MainNavigationModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        MainNavigationModule::class,
        LoginNavigationModule::class,
        GuideNavigationModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface NavigationModule