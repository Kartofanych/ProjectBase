package com.search.impl.ui

import com.search.impl.data.models.local.ActivityGroup
import com.search.impl.data.models.local.Attraction

sealed interface ListUiState {

    object Error : ListUiState

    object Loading : ListUiState

    class Content(
        val hint: String,
        val recommendedList: List<Attraction>,
        val activityGroups: List<ActivityGroup>
    ) : ListUiState
}