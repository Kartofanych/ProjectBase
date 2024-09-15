package com.search.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.search.impl.domain.SearchInteractor
import com.search.impl.ui.SearchUiState.SearchResultsState
import com.search.impl.ui.SearchUiState.SearchScreenState
import com.splash.api.domain.CitiesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val searchInteractor: SearchInteractor,
) : ViewModel() {

    private val _uiEvent = Channel<SearchEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(
        SearchUiState.empty(cities = citiesRepository.cities().map { it.name })
    )
    val uiStateFlow: StateFlow<SearchUiState>
        get() = _uiStateFlow

    private var searchText = ""
        set(value) {
            currentList.clear()
            field = value
        }
    private var cursor = ""
    private val currentList = mutableListOf<ActivityEntity>()

    private fun searchRequest() {
        _uiStateFlow.update {
            it.copy(
                state = SearchScreenState.SearchResults(
                    list = currentList,
                    state = SearchResultsState.Loading
                )
            )
        }

        viewModelScope.launch {
            when (val result = searchInteractor.search(searchText, cursor)) {
                is ResponseState.Error -> {
                    _uiStateFlow.update {
                        it.copy(
                            state = SearchScreenState.SearchResults(
                                state = SearchResultsState.Results,
                                list = currentList
                            )
                        )
                    }
                }

                is ResponseState.Success -> {
                    currentList.addAll(result.data.items)
                    cursor = result.data.cursor
                    _uiStateFlow.update {
                        it.copy(
                            state = SearchScreenState.SearchResults(
                                state = SearchResultsState.Loading,
                                list = currentList
                            )
                        )
                    }
                }
            }
        }

    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.ChangeSearchText -> {
                searchText = action.search
                if (action.search.isBlank()) {
                    _uiStateFlow.update {
                        it.copy(
                            state = SearchScreenState.ZeroSearch(
                                popularCities = citiesRepository.cities().map { city -> city.name }
                            )
                        )
                    }
                } else {
                    searchRequest()
                }
            }

            is SearchAction.ActivityClicked -> {
                when (action.entity.type) {
                    ActivityEntity.ActivityType.LANDMARK -> {

                    }

                    ActivityEntity.ActivityType.SERVICE -> {

                    }
                }
            }
        }
    }
}
