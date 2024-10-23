package com.favourites.impl.data.mappers

import com.favourites.impl.data.models.dto.FavoriteAttractionDto
import com.favourites.impl.data.models.dto.FavoritesResponseDto
import com.favourites.impl.data.models.dto.ProfileInfoDto
import com.favourites.impl.data.models.local.FavoriteAttraction
import com.favourites.impl.data.models.local.FavoritesResponse
import com.favourites.impl.data.models.local.ProfileInfo
import javax.inject.Inject

class FavoritesMapper @Inject constructor() {

    fun map(dto: FavoritesResponseDto): FavoritesResponse {
        return FavoritesResponse(
            //profileInfo = mapProfileInfo(dto.profile),
            attractions = dto.attractions.mapIndexed { index, item -> mapAttraction(index, item) }
        )
    }

    private fun mapProfileInfo(dto: ProfileInfoDto): ProfileInfo {
        return ProfileInfo(
            icon = dto.icon,
            mail = dto.mail,
            name = dto.name
        )
    }

    private fun mapAttraction(index: Int, dto: FavoriteAttractionDto): FavoriteAttraction {
        return FavoriteAttraction(
            id = dto.id,
            index = index,
            icon = dto.icon,
            title = dto.title,
            subtitle = dto.subtitle,
            rating = dto.rating,
            isLiked = true
        )
    }
}