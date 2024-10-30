package com.favourites.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FavoritesResponseDto(

    //@SerializedName("profile_info")
    //val profile: ProfileInfoDto,

    @SerializedName("attractions")
    val attractions: List<FavoriteAttractionDto>
)