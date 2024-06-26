package com.inno.landmark.data

data class LandmarkResponse(
    val name: String,
    val info: String,
    val address: String,
    val images: List<String>,
    val categories: List<LandmarkCategory>,
    val audioGuides: List<LandmarkAudioGid>
)