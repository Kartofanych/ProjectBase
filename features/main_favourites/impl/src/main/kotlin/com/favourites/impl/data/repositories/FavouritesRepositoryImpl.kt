package com.favourites.impl.data.repositories

import com.favourites.api.domain.FavoritesRepository
import com.main.common.di.MainScope
import dagger.Reusable
import javax.inject.Inject

@MainScope
@Reusable
class FavouritesRepositoryImpl @Inject constructor() : FavoritesRepository {

    private var shouldUpdate: Boolean = true

    override fun shouldUpdate(): Boolean {
        return shouldUpdate.also { shouldUpdate = false }
    }

    override fun requestUpdate() {
        shouldUpdate = true
    }
}