package com.search_filters.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class SearchFiltersDto(

    @SerializedName("distance")
    val distance: Float,

    @SerializedName("cities")
    val cities: List<String>,

    @SerializedName("min_rating")
    val minRating: Float,
)
