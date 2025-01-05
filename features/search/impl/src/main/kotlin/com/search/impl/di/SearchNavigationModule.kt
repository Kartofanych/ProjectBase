package com.search.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.search.api.SearchEntry
import com.search.impl.SearchEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(SearchEntry::class)
    fun searchEntry(guideEntry: SearchEntryImpl): FeatureEntry
}