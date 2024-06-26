package com.inno.landmark.ui

import com.inno.landmark.data.LandmarkResponse

sealed interface LandMarkState {
    object Error : LandMarkState
    object Loading : LandMarkState
    class Content(val landmark: LandmarkResponse) : LandMarkState
}