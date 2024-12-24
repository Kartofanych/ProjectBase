package com.favourites.impl.data.models.local

data class FavoritesResponse(
    val profileInfo: ProfileInfo,
    val attractions: List<FavoriteAttraction>
)