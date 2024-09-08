package com.favourites.impl.di

import com.favourites.impl.ui.FavouritesViewModel
import javax.inject.Inject

class FavouritesDependencies @Inject constructor(
    val favouritesViewModel: FavouritesViewModel
)