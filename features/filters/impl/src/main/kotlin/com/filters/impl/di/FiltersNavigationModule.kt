package com.filters.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
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