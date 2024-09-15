package com.attraction.impl.di

import com.attraction.api.AttractionEntry
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.attraction.impl.AttractionEntryImpl
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