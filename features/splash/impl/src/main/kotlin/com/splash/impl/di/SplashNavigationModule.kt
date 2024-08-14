package com.splash.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.splash.api.SplashEntry
import com.splash.impl.SplashEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(SplashEntry::class)
    fun filtersNavigation(filterEntry: SplashEntryImpl): FeatureEntry
}
