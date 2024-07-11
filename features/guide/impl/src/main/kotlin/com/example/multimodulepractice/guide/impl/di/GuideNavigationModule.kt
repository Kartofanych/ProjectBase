package com.example.multimodulepractice.guide.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.example.multimodulepractice.guide.GuideEntry
import com.example.multimodulepractice.guide.impl.GuideEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GuideNavigationModule {
    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(GuideEntry::class)
    fun guideNavigation(guideEntry: GuideEntryImpl): FeatureEntry
}