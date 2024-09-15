package com.main_map.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.main_map.api.MapFeatureEntry
import com.main_map.impl.MapFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MapNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(MapFeatureEntry::class)
    fun mainNavigation(mainFeatureImpl: MapFeatureImpl): FeatureEntry
}