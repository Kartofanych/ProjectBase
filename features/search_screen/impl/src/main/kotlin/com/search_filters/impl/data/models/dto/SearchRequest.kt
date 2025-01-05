package com.search_filters.impl.data.models.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
class SearchRequest(

    @SerializedName("query")
    val query: String,

    @SerializedName("position")
    val geoPoint: GeoPointDto,

    @SerializedName("cursor")
    val cursor: String,

    @SerializedName("filters")
    val filters: SearchFiltersDto
)