package com.start.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.start.api.StartEntry
import com.start.impl.StartEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface StartNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(StartEntry::class)
    fun startEntry(entry: StartEntryImpl): FeatureEntry
}
