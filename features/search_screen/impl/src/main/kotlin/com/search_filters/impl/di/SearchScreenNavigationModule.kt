package com.search_filters.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.search_filters.api.data.SearchScreenEntry
import com.search_filters.impl.SearchScreenEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchScreenNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(SearchScreenEntry::class)
    fun searchEntry(searchScreenEntry: SearchScreenEntryImpl): FeatureEntry
}
