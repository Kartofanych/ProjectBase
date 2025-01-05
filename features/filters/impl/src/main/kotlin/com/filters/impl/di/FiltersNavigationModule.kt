package com.filters.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.filters.api.FiltersEntry
import com.filters.impl.FiltersEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FiltersNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(FiltersEntry::class)
    fun filtersNavigation(filterEntry: FiltersEntryImpl): FeatureEntry
}