package com.attraction.impl.ui

import com.attraction.impl.data.models.local.Attraction

sealed interface AttractionUiState {
    object Error : AttractionUiState
    object Loading : AttractionUiState
    data class Content(val landmark: Attraction) : AttractionUiState
}