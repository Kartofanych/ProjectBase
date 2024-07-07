package com.inno.impl.repositories

import com.example.common.models.local.ResponseState
import com.example.common.utils.runWithMinTime
import com.inno.impl.data.interactors.LandmarkInteractor
import com.inno.landmark.ui.Landmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandmarkRepository @Inject constructor(
    private val scope: CoroutineScope,
    private val landmarkInteractor: LandmarkInteractor
) {

    private val _currentLandmark = MutableStateFlow<Landmark?>(null)
    val currentLandmark = _currentLandmark.asStateFlow()

    fun changeCurrentLandmark(landmark: Landmark) {
        _currentLandmark.update { landmark }
    }

    fun dismissLandmark() {
        _currentLandmark.update { null }
    }

    //TODO create viewmodel for main screen
    fun getLandmark(landmarkId: String) {
        changeCurrentLandmark(Landmark(landmarkId, Landmark.LandmarkState.Loading))

        scope.launch {
            when (val result = runWithMinTime({ landmarkInteractor.getLandmarkInfo(landmarkId) })) {
                is ResponseState.Error -> {
                    changeCurrentLandmark(
                        Landmark(landmarkId, Landmark.LandmarkState.Error)
                    )
                }

                is ResponseState.Success -> {
                    changeCurrentLandmark(
                        Landmark(landmarkId, Landmark.LandmarkState.Content(result.data))
                    )
                }
            }
        }
    }

}