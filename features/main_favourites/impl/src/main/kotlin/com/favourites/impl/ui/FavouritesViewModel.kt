package com.favourites.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.favourites.api.domain.FavoritesRepository
import com.favourites.api.domain.LikeInteractor
import com.favourites.impl.data.interactors.FavouritesInteractor
import com.favourites.impl.ui.FavouritesUiState.FavouritesState
import com.main.common.di.MainScope
import dagger.Reusable
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
@Reusable
class FavouritesViewModel @Inject constructor(
    private val authInfoManager: AuthInfoManager,
    private val interactor: FavouritesInteractor,
    private val likeInteractor: LikeInteractor,
    private val favoritesRepository: FavoritesRepository,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(FavouritesUiState.empty())
    val uiStateFlow: StateFlow<FavouritesUiState>
        get() = _uiStateFlow

    private val _uiEvent = Channel<FavouritesUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val likeEvent = MutableSharedFlow<LikeEvent>()
    private var likeJob: Job? = null

    private var favouritesJob: Job? = null

    init {
        collectProfileUpdates()
        collectLikeEvents()
    }

    private fun collectLikeEvents() {
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            likeEvent.collectLatest { event ->
                changeFavorite(
                    index = event.index,
                    id = event.id,
                    isLiked = event.isLiked
                )
            }
        }
    }

    fun onAttach() {
        if (favoritesRepository.shouldUpdate()) {
            getFavourites()
        }
    }

    private fun getFavourites() {
        _uiStateFlow.update { it.copy(state = FavouritesState.Loading) }
        favouritesJob?.cancel()
        favouritesJob = viewModelScope.launch {
            when (val result = interactor.favorite()) {
                is ResponseState.Error.Unauthorized -> {
                    _uiStateFlow.update { it.copy(state = FavouritesState.Unauthorized) }
                }

                is ResponseState.Error -> {
                    _uiStateFlow.update { it.copy(state = FavouritesState.Error) }
                }

                is ResponseState.Success -> {
                    with(result.data) {
                        _uiStateFlow.update {
                            it.copy(
                                state = FavouritesState.Authorized(
                                    attractions,
                                    FavouritesUiState.UserProfile(
                                        name = profileInfo.name,
                                        image = profileInfo.icon,
                                        promoCount = profileInfo.promoCount,
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun changeFavorite(id: String, isLiked: Boolean, index: Int) {
        when (likeInteractor.changeFavorite(id, isLiked)) {
            is ResponseState.Error -> {
                _uiStateFlow.update { uiState ->
                    if (uiState.state is FavouritesState.Authorized) {
                        val list = uiState.state.items.toMutableList()
                        val newItem = list[index].copy(isLiked = isLiked)
                        list[index] = newItem
                        uiState.copy(state = uiState.state.copy(items = list))
                    } else {
                        uiState
                    }
                }
            }

            is ResponseState.Success -> {
                favoritesRepository.requestUpdate()
            }
        }
    }

    private fun collectProfileUpdates() {
        viewModelScope.launch {
            authInfoManager.authInfoFlow().drop(1).collectLatest { _ ->
                favoritesRepository.requestUpdate()
            }
        }
    }

    fun onAction(action: FavouritesAction) {
        when (action) {
            FavouritesAction.OnLogOut -> {
                viewModelScope.launch {
                    authInfoManager.updateAuthInfo(AuthInfo.Guest)
                    _uiEvent.send(FavouritesUiEvent.LogOut)
                }
            }

            is FavouritesAction.OnOpenAttraction -> {
                viewModelScope.launch {
                    _uiEvent.send(FavouritesUiEvent.OpenAttraction(action.attractionId))
                }
            }

            FavouritesAction.OnLogIn -> {
                viewModelScope.launch {
                    _uiEvent.send(FavouritesUiEvent.LogOut)
                }
            }

            is FavouritesAction.ChangeProfileModalVisibility -> {
                _uiStateFlow.update {
                    it.copy(isModalVisible = action.isVisible)
                }
            }

            is FavouritesAction.OnLikeChanged -> {
                (_uiStateFlow.value.state as? FavouritesState.Authorized)?.apply {
                    val list = items.toMutableList()
                    val newItem = list[action.index].copy(isLiked = !list[action.index].isLiked)
                    list[action.index] = newItem
                    _uiStateFlow.update { it.copy(state = copy(items = list)) }
                    viewModelScope.launch {
                        likeEvent.emit(LikeEvent(newItem.id, newItem.isLiked, action.index))
                    }
                }
            }

            FavouritesAction.OnReload -> getFavourites()

            FavouritesAction.OpenPromo -> {
                _uiStateFlow.update { it.copy(isModalVisible = false) }
                viewModelScope.launch { _uiEvent.send(FavouritesUiEvent.OpenPromo) }
            }
        }
    }

    private data class LikeEvent(
        val id: String,
        val isLiked: Boolean,
        val index: Int
    )
}