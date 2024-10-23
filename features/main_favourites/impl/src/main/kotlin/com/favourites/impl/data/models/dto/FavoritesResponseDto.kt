package com.favourites.impl.data.models.dto

import com.google.gson.annotations.SerializedName

data class FavoritesResponseDto(

    //@SerializedName("profile_info")
    //val profile: ProfileInfoDto,

    @SerializedName("attractions")
    val attractions: List<FavoriteAttractionDto>
)