package com.service.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.runWithMinTime
import com.example.travelling.geo.repository.GeoRepository
import com.reviews.api.SendReviewInteractor
import com.service.impl.di.ServiceScope
import com.service.impl.domain.ServiceInteractor
import com.service.impl.ui.ServiceUiState.DataState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ServiceScope
class ServiceViewModel @Inject constructor(
    private val serviceInteractor: ServiceInteractor,
    private val serviceId: String,
    private val deeplinkHandler: DeeplinkHandler,
    private val authInfoManager: AuthInfoManager,
    private val sendReviewInteractor: SendReviewInteractor,
    private val geoRepository: GeoRepository,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<ServiceUiEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiStateFlow =
        MutableStateFlow(ServiceUiState(state = DataState.Loading))
    val uiStateFlow: StateFlow<ServiceUiState>
        get() = _uiStateFlow

    private val _reviewModalStateFlow = MutableStateFlow<ReviewModalState>(ReviewModalState.Hidden)
    val reviewModalStateFlow: StateFlow<ReviewModalState>
        get() = _reviewModalStateFlow

    init {
        Analytics.reportOpenFeature(feature = "service", mapOf("id" to serviceId))
        getService()

        viewModelScope.launch {
            _uiStateFlow.update { it.copy(isAuthorized = authInfoManager.isAuthorized()) }
        }
    }

    private fun getService() {
        _uiStateFlow.update { it.copy(state = DataState.Loading) }
        viewModelScope.launch {
            when (val result = runWithMinTime({ serviceInteractor.service(serviceId) })) {
                is ResponseState.Error -> _uiStateFlow.update { it.copy(state = DataState.Error) }
                is ResponseState.Success -> _uiStateFlow.update {
                    it.copy(state = DataState.Content(result.data))
                }
            }
        }
    }

    fun onAction(action: ServiceAction) {
        when (action) {
            ServiceAction.OnBackPressed -> {
                viewModelScope.launch {
                    _uiEvent.tryEmit(ServiceUiEvent.OnBackPressed)
                }
            }

            ServiceAction.OnReload -> getService()

            is ServiceAction.Deeplink -> {
                Analytics.reportFeatureAction(
                    feature = "service",
                    action = "connection to ${action.deeplink}"
                )
                deeplinkHandler.handleDeeplink(action.deeplink)
            }

            is ServiceAction.ChangeReviewModalVisibility -> {
                _reviewModalStateFlow.update { _ ->
                    when (action.visible) {
                        true -> ReviewModalState.Default(5)
                        false -> ReviewModalState.Hidden
                    }
                }
            }

            ServiceAction.ChangeScheduleVisibility -> {
                _uiStateFlow.update { state -> state.copy(isScheduleVisible = !state.isScheduleVisible) }
            }

            ServiceAction.OpenOnMap -> {
                /*val userLocation = geoRepository.geoInfoImmediately().currentPoint
                val (userLat, userLon) = userLocation
                val targetLocation =
                    (uiStateFlow.value.state as? ServiceUiState.DataState.Content)?.service?.location
                val (attrLat, attrLon) = targetLocation
                    ?: return

                val preferredRouteType =
                    routeTypeInteractor.bestOption(userLocation, targetLocation).value
                deeplinkHandler.handleDeeplink(
                    "yandexmaps://maps.yandex.ru/?rtext=$userLat,$userLon~$attrLat,$attrLon&rtt=$preferredRouteType"
                )*/
            }

            ServiceAction.OpenReviews -> {
                _uiEvent.tryEmit(ServiceUiEvent.OpenReviews(serviceId))
            }

            is ServiceAction.ChangeReviewStars -> {
                _reviewModalStateFlow.update { ReviewModalState.Default(action.starCount) }
            }

            is ServiceAction.SendReview -> {
                sendReview(text = action.text, stars = _reviewModalStateFlow.value.starsCount)
            }

            ServiceAction.ChangeCallBottomSheetVisibility -> {
                _uiStateFlow.update { it.copy(isCallBottomSheetVisible = !it.isCallBottomSheetVisible) }
            }
        }
    }

    private fun sendReview(text: String, stars: Int) {
        _reviewModalStateFlow.update { _ -> ReviewModalState.Loading }
        viewModelScope.launch {
            when (sendReviewInteractor.sendReview(id = serviceId, text = text, stars = stars)) {
                is ResponseState.Error -> {
                    _reviewModalStateFlow.update { ReviewModalState.Error }
                }

                is ResponseState.Success -> {
                    _reviewModalStateFlow.update { ReviewModalState.Hidden }
                    getService()
                }
            }
        }
    }
}
