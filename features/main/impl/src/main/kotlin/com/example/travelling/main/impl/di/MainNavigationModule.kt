package com.example.travelling.main.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.example.travelling.main.MainFeatureEntry
import com.example.travelling.main.impl.MainFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(MainFeatureEntry::class)
    fun mainNavigation(mainFeatureImpl: MainFeatureImpl): FeatureEntry
}