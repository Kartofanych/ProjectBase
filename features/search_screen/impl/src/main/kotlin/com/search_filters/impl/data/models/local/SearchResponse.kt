package com.search_filters.impl.data.models.local

import com.example.multimodulepractice.common.data.models.local.ActivityEntity

class SearchResponse(
    val cursor: String?,
    val items: List<ActivityEntity>
)
