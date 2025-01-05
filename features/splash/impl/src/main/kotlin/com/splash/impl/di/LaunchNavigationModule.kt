package com.splash.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.splash.api.LaunchEntry
import com.splash.impl.LaunchEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LaunchNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(LaunchEntry::class)
    fun launchNavigation(entry: LaunchEntryImpl): FeatureEntry
}
