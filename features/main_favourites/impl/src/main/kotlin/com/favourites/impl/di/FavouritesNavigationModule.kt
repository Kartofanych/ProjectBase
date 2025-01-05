package com.favourites.impl.di

import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.favourites.api.FavouritesFeatureEntry
import com.favourites.impl.FavouritesFeatureImpl
import com.main.common.di.MainScope
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
interface FavouritesNavigationModule {

    @Binds
    @MainScope
    @IntoMap
    @Reusable
    @FeatureEntryKey(FavouritesFeatureEntry::class)
    fun favouritesNavigation(mainFeatureImpl: FavouritesFeatureImpl): FeatureEntry
}