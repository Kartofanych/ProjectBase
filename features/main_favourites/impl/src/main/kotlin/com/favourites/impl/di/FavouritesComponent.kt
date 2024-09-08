package com.favourites.impl.di

import dagger.Component

@FavouritesScope
@Component(
    dependencies = [FavouritesDependencies::class]
)
interface FavouritesComponent {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: FavouritesDependencies
        ): FavouritesComponent
    }
}