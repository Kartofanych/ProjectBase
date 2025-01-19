package com.attraction.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attraction.impl.domain.AttractionInteractor
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.common.utils.Analytics
import com.example.travelling.geo.repository.GeoRepository
import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AttractionViewModel @Inject constructor(
    private val attractionId: String,
    private val attractionInteractor: AttractionInteractor,
    private val likeInteractor: LikeInteractor,
    private val favoritesRepository: FavoritesRepository,
    private val deeplinkHandler: DeeplinkHandler,
    private val geoRepository: GeoRepository,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<AttractionEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiStateFlow = MutableStateFlow<AttractionUiState>(AttractionUiState.Loading)
    val uiStateFlow: StateFlow<AttractionUiState>
        get() = _uiStateFlow

    private val _reviewModalStateFlow = MutableStateFlow<ReviewModalState>(ReviewModalState.Hidden)
    val reviewModalStateFlow: StateFlow<ReviewModalState>
        get() = _reviewModalStateFlow

    private val likeEvent = MutableSharedFlow<Boolean>()
    private var likeJob: Job? = null

    init {
        Analytics.reportOpenFeature("attraction", mapOf("id" to attractionId))
        loadAttraction()
        collectLikeEvents()
    }

    private fun collectLikeEvents() {
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            likeEvent.collectLatest { isLiked ->
                Analytics.reportFeatureAction(
                    feature = "attraction",
                    action = if (isLiked) "like" else "unlike"
                )
                when (likeInteractor.changeFavorite(attractionId, isLiked)) {
                    is ResponseState.Error -> _uiStateFlow.update { state ->
                        if (state is AttractionUiState.Content) {
                            state.copy(state.landmark.copy(isLiked = !isLiked))
                        } else state
                    }

                    is ResponseState.Success -> {
                        favoritesRepository.requestUpdate()
                    }
                }
            }
        }
    }

    private fun loadAttraction() {
        _uiStateFlow.update { AttractionUiState.Loading }

        viewModelScope.launch {
            when (val result = attractionInteractor.getLandmarkInfo(attractionId)) {
                is ResponseState.Error -> {
                    _uiStateFlow.update { AttractionUiState.Error }
                }

                is ResponseState.Success -> {
                    _uiStateFlow.update { AttractionUiState.Content(result.data) }
                }
            }
        }
    }


    fun onAction(action: AttractionAction) {
        when (action) {
            AttractionAction.OpenGuide -> {
                _uiEvent.tryEmit(AttractionEvent.OpenGuide(attractionId))
            }

            is AttractionAction.OpenObject -> {
                _uiEvent.tryEmit(AttractionEvent.OpenObject(id = action.id, type = action.type))
            }

            AttractionAction.RecallAttraction -> loadAttraction()

            is AttractionAction.OnLikeChanged -> {
                changeFavorites()
            }

            AttractionAction.OnBackPressed -> {
                _uiEvent.tryEmit(AttractionEvent.OnBackPressed)
            }

            AttractionAction.OpenOnMap -> {
                val (userLat, userLon) = geoRepository.geoInfoImmediately().currentPoint
                val (attrLat, attrLon) = (uiStateFlow.value as? AttractionUiState.Content)?.landmark?.location
                    ?: return
                deeplinkHandler.handleDeeplink(
                    "yandexmaps://maps.yandex.ru/?rtext$userLat,$userLon~$attrLat,$attrLon&rtt=auto"
                )
            }

            AttractionAction.ChangeScheduleVisibility -> {
                _uiStateFlow.update { state ->
                    (state as? AttractionUiState.Content)?.let {
                        state.copy(
                            landmark = it.landmark.copy(
                                schedule = it.landmark.schedule.copy(
                                    isVisible = !it.landmark.schedule.isVisible
                                )
                            )
                        )
                    } ?: state
                }
            }

            is AttractionAction.ChangeReviewModalVisibility -> {
                _reviewModalStateFlow.update { _ ->
                    when (action.visible) {
                        true -> ReviewModalState.Default(5)
                        false -> ReviewModalState.Hidden
                    }
                }
            }

            is AttractionAction.SendReview -> {
                sendReview(text = action.text, stars = _reviewModalStateFlow.value.starsCount)
            }

            is AttractionAction.ChangeReviewStars -> {
                _reviewModalStateFlow.update { ReviewModalState.Default(action.starsCount) }
            }

            AttractionAction.OpenReviews -> {
                _uiEvent.tryEmit(AttractionEvent.OpenReviews(attractionId))
            }
        }
    }

    private fun sendReview(text: String, stars: Int) {
        _reviewModalStateFlow.update { _ -> ReviewModalState.Loading }
        viewModelScope.launch {
            when (attractionInteractor.sendReview(id = attractionId, text = text, stars = stars)) {
                is ResponseState.Error -> {
                    _reviewModalStateFlow.update { ReviewModalState.Error }
                }

                is ResponseState.Success -> {
                    _reviewModalStateFlow.update { ReviewModalState.Hidden }
                    loadAttraction()
                }
            }
        }
    }

    private fun changeFavorites() {
        _uiStateFlow.update { state ->
            (state as? AttractionUiState.Content)?.let {
                val isLiked = !state.landmark.isLiked
                state.copy(state.landmark.copy(isLiked = isLiked)).also {
                    viewModelScope.launch { likeEvent.emit(isLiked) }
                }
            } ?: state
        }
    }
}
