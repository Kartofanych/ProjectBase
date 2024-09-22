package com.search_filters.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
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