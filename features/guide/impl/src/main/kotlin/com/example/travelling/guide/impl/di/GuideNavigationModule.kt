package com.example.travelling.guide.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.example.travelling.guide.GuideEntry
import com.example.travelling.guide.impl.GuideEntryImpl
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