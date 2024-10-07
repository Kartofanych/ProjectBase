package com.search.impl.data.models.local

data class ListResponse(
    val hint: String,
    val attractions: List<Attraction>,
    val activityGroups: List<ActivityGroup>
)