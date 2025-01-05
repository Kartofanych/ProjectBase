package com.search_filters.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.search_filters.api.data.SearchFiltersEntry
import com.search_filters.impl.SearchFiltersEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchFiltersNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(SearchFiltersEntry::class)
    fun searchFiltersEntry(entry: SearchFiltersEntryImpl): FeatureEntry
}