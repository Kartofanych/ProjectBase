package com.search.impl.di

import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.main.common.di.MainScope
import com.search.api.SearchFeatureEntry
import com.search.impl.ListFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
interface ListNavigationModule {

    @Binds
    @MainScope
    @IntoMap
    @Reusable
    @FeatureEntryKey(SearchFeatureEntry::class)
    fun searchNavigation(mainFeatureImpl: ListFeatureImpl): FeatureEntry
}