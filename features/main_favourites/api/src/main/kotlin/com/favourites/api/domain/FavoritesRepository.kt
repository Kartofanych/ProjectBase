package com.favourites.api.domain

interface FavoritesRepository {

    fun shouldUpdate(): Boolean

    fun requestUpdate()
}