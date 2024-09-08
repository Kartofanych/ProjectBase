package com.main.common.domain

import com.main.common.data.local.Landmark
import kotlinx.coroutines.flow.StateFlow

interface AttractionRepository {

    fun currentLandmark(): StateFlow<Landmark?>

    fun dismissLandmark()

    fun getLandmark(landmarkId: String)
}