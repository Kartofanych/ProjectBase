package com.example.multimodulepractice.main.impl.repositories

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.example.multimodulepractice.landmark.ui.Landmark
import com.example.multimodulepractice.main.impl.data.interactors.LandmarkInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScope
class AttractionRepositoryImpl @Inject constructor(
    private val scope: CoroutineScope,
    private val landmarkInteractor: LandmarkInteractor
) : AttractionRepository {

    private val _currentLandmark = MutableStateFlow<Landmark?>(null)
    override fun currentLandmark(): StateFlow<Landmark?> = _currentLandmark.asStateFlow()


    private fun changeCurrentLandmark(landmark: Landmark) {
        _currentLandmark.update { landmark }
    }

    override fun dismissLandmark() {
        _currentLandmark.update { null }
    }

    //TODO create viewmodel for main screen
    override fun getLandmark(landmarkId: String) {
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