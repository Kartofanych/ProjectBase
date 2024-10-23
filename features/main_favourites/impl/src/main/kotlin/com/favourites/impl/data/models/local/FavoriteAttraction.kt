package com.favourites.impl.data.models.local

data class FavoriteAttraction(
    val id: String,
    val index: Int,
    val icon: String,
    val title: String,
    val subtitle: String,
    val rating: Float,
    val isLiked: Boolean
)
