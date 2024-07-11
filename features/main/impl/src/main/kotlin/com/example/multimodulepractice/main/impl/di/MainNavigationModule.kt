package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.main.impl.MainFeatureImpl
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