package com.example.multimodulepractice.main.impl.repositories

import com.example.multimodulepractice.landmark.ui.Landmark
import kotlinx.coroutines.flow.StateFlow

interface AttractionRepository {

    fun currentLandmark(): StateFlow<Landmark?>

    fun dismissLandmark()

    fun getLandmark(landmarkId: String)

}