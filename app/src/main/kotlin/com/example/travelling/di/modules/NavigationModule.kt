package com.example.travelling.di.modules

import com.attraction.impl.di.AttractionNavigationModule
import com.example.travelling.audio_guide.impl.di.AudioGuideNavigationModule
import com.example.travelling.guide.impl.di.GuideNavigationModule
import com.example.travelling.login.impl.di.LoginNavigationModule
import com.example.travelling.main.impl.di.MainNavigationModule
import com.filters.impl.di.FiltersNavigationModule
import com.onboarding.impl.di.OnboardingNavigationModule
import com.promo.impl.di.PromoNavigationModule
import com.reviews.impl.di.ReviewsNavigationModule
import com.search.impl.di.SearchNavigationModule
import com.service.impl.di.ServiceNavigationModule
import com.splash.impl.di.LaunchNavigationModule
import dagger.Module

@Module(
    includes = [
        MainNavigationModule::class,
        LoginNavigationModule::class,
        GuideNavigationModule::class,
        AudioGuideNavigationModule::class,
        FiltersNavigationModule::class,
        LaunchNavigationModule::class,
        ServiceNavigationModule::class,
        SearchNavigationModule::class,
        AttractionNavigationModule::class,
        PromoNavigationModule::class,
        ReviewsNavigationModule::class,
        OnboardingNavigationModule::class,
    ]
)
interface NavigationModule