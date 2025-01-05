package com.attraction.impl.di

import com.attraction.api.AttractionEntry
import com.attraction.impl.AttractionEntryImpl
import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AttractionNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(AttractionEntry::class)
    fun searchEntry(entry: AttractionEntryImpl): FeatureEntry
}