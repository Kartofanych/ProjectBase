package com.inno.landmark.ui

import com.inno.landmark.data.LandmarkResponse

data class Landmark(
    val id: String,
    val state: LandmarkState
) {

    sealed interface LandmarkState {
        object Error : LandmarkState
        object Loading : LandmarkState
        class Content(val landmark: LandmarkResponse) : LandmarkState
    }

    companion object {
        val EMPTY = Landmark(
            "",
            LandmarkState.Loading
        )
    }
}