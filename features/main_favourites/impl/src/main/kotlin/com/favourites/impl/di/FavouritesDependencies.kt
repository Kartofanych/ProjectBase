package com.favourites.impl.di

import com.example.travelling.auth.AuthInfoManager
import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import com.favourites.impl.ui.FavouritesViewModel
import javax.inject.Inject

class FavouritesDependencies @Inject constructor(
    val favouritesViewModel: FavouritesViewModel,
    val authInfoManager: AuthInfoManager,
    val likeInteractor: LikeInteractor,
    val favoritesRepository: FavoritesRepository,
)