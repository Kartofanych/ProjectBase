package com.favourites.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.common.di.MainScope
import com.main.common.domain.AttractionRepository
import com.favourites.impl.ui.FavouritesUiState.ProfileMode
import com.main.common.domain.RecommendedAttractionsRepository
import dagger.Reusable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
@Reusable
class FavouritesViewModel @Inject constructor(
    private val authInfoManager: AuthInfoManager,
    private val recommendedAttractionsRepository: RecommendedAttractionsRepository,
    private val attractionRepository: AttractionRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(FavouritesUiState())
    val uiStateFlow: StateFlow<FavouritesUiState>
        get() = _uiStateFlow

    private val _uiEvent = Channel<FavouritesUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        collectProfileUpdates()
        //TODO
        collectRecommendations()
    }

    private fun collectRecommendations() {
        viewModelScope.launch {
            recommendedAttractionsRepository.recommendedAttractions.collectLatest { list ->
                _uiStateFlow.update {
                    it.copy(recommendedList = list)
                }
            }
        }
    }

    private fun collectProfileUpdates() {
        viewModelScope.launch {
            authInfoManager.authInfoFlow().collectLatest { auth ->
                when (auth) {
                    AuthInfo.Guest -> {
                        _uiStateFlow.update {
                            it.copy(mode = ProfileMode.GuestProfile)
                        }
                    }

                    is AuthInfo.User -> {
                        _uiStateFlow.update {
                            it.copy(
                                mode = ProfileMode.UserProfile(
                                    auth.name,
                                    auth.gmail,
                                    auth.image
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun onProfileAction(action: FavouritesAction) {
        when (action) {
            FavouritesAction.OnLogOut -> {
                viewModelScope.launch {
                    authInfoManager.updateAuthInfo(AuthInfo.Guest)
                    _uiEvent.send(FavouritesUiEvent.LogOut)
                }
            }

            is FavouritesAction.OnOpenAttraction -> {
                attractionRepository.getLandmark(action.attractionId)
            }
        }
    }
}