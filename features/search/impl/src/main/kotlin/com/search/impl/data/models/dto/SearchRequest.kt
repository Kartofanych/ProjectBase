package com.search.impl.data.models.dto

import com.example.multimodulepractice.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

class SearchRequest(
    @SerializedName("query")
    val query: String,

    @SerializedName("position")
    val geoPoint: GeoPointDto,

    @SerializedName("cursor")
    val cursor: String,

    //TODO
    //@SerializedName("filters")
)