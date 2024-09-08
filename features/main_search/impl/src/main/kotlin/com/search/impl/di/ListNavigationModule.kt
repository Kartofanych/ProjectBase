package com.search.impl.di

import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
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