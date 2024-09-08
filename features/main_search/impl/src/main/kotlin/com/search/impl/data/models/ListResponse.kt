package com.search.impl.data.models

import com.main.common.data.local.Attraction

data class ListResponse(
    val recommendList: List<Attraction>,
    val closeList: List<Attraction>
)