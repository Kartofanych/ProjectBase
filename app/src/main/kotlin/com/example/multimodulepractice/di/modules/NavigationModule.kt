package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.audio_guide.impl.di.AudioGuideNavigationModule
import com.example.multimodulepractice.guide.impl.di.GuideNavigationModule
import com.example.multimodulepractice.main.impl.di.MainNavigationModule
import com.example.multimodulepractice.login.impl.di.LoginNavigationModule
import com.filters.impl.di.FiltersNavigationModule
import dagger.Module

@Module(
    includes = [
        MainNavigationModule::class,
        LoginNavigationModule::class,
        GuideNavigationModule::class,
        AudioGuideNavigationModule::class,
        FiltersNavigationModule::class
    ]
)
interface NavigationModule