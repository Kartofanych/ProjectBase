package com.example.travelling.login.impl.di

import com.start.impl.di.ProcessNavigationModule
import com.start.impl.di.StartNavigationModule
import dagger.Module

@Module(
    includes = [
        StartNavigationModule::class,
        ProcessNavigationModule::class
    ]
)
class NavigationModule
