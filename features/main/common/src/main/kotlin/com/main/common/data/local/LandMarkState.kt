package com.main.common.data.local

data class Landmark(
    val id: String,
    val state: LandmarkState
) {

    sealed interface LandmarkState {
        object Error : LandmarkState
        object Loading : LandmarkState
        class Content(val landmark: LandmarkResponse) : LandmarkState
    }
}