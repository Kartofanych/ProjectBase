package com.example.multimodulepractice.main.impl.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.main.impl.di.MainScope
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.repositories.RecommendedAttractionsRepository
import com.example.multimodulepractice.main.impl.ui.profile.ProfileUiState.ProfileMode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class ProfileViewModel @Inject constructor(
    private val authInfoManager: AuthInfoManager,
    private val recommendedAttractionsRepository: RecommendedAttractionsRepository,
    private val attractionRepository: AttractionRepository
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(ProfileUiState())
    val uiStateFlow: StateFlow<ProfileUiState>
        get() = _uiStateFlow

    private val _uiEvent = Channel<ProfileUiEvent>()
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

    fun onProfileAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnLogOut -> {
                viewModelScope.launch {
                    authInfoManager.updateAuthInfo(AuthInfo.Guest)
                    _uiEvent.send(ProfileUiEvent.LogOut)
                }
            }

            is ProfileAction.OnOpenAttraction -> {
                attractionRepository.getLandmark(action.attractionId)
            }
        }
    }
}