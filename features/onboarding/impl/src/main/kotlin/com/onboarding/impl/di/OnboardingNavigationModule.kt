package com.onboarding.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.onboarding.api.OnboardingEntry
import com.onboarding.impl.OnboardingEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface OnboardingNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(OnboardingEntry::class)
    fun searchEntry(entry: OnboardingEntryImpl): FeatureEntry
}