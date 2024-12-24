package com.example.multimodulepractice.di.modules

import com.attraction.impl.di.AttractionNavigationModule
import com.example.multimodulepractice.audio_guide.impl.di.AudioGuideNavigationModule
import com.example.multimodulepractice.guide.impl.di.GuideNavigationModule
import com.example.multimodulepractice.main.impl.di.MainNavigationModule
import com.example.multimodulepractice.login.impl.di.LoginNavigationModule
import com.filters.impl.di.FiltersNavigationModule
import com.promo.impl.di.PromoNavigationModule
import com.search.impl.di.SearchNavigationModule
import com.service.impl.di.ServiceNavigationModule
import com.splash.impl.di.SplashNavigationModule
import dagger.Module

@Module(
    includes = [
        MainNavigationModule::class,
        LoginNavigationModule::class,
        GuideNavigationModule::class,
        AudioGuideNavigationModule::class,
        FiltersNavigationModule::class,
        SplashNavigationModule::class,
        ServiceNavigationModule::class,
        SearchNavigationModule::class,
        AttractionNavigationModule::class,
        PromoNavigationModule::class
    ]
)
interface NavigationModule