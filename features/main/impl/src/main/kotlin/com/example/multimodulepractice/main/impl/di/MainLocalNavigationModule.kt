package com.example.multimodulepractice.main.impl.di

import com.favourites.impl.di.FavouritesNavigationModule
import com.main_map.impl.di.MapNavigationModule
import com.search.impl.di.ListNavigationModule
import dagger.Module

@Module(
    includes = [
        MapNavigationModule::class,
        ListNavigationModule::class,
        FavouritesNavigationModule::class
    ]
)
interface MainLocalNavigationModule