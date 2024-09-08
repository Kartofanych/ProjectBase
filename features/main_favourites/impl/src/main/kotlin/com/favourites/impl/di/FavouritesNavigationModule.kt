package com.favourites.impl.di

import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.favourites.api.FavouritesFeatureEntry
import com.favourites.impl.FavouritesFeatureImpl
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