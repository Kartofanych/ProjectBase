package com.search.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.search.impl.domain.SearchInteractor
import com.search.impl.ui.SearchUiState.SearchResultsState
import com.search.impl.ui.SearchUiState.SearchScreenState
import com.splash.api.domain.CitiesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
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

    private val queryFlow = MutableStateFlow("")

    private var cursor = ""
    private val currentList = mutableListOf<ActivityEntity>()
    private var searchJob: Job? = null

    init {
        collectSearch()
    }

    private fun searchRequest(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiStateFlow.update {
                it.copy(
                    state = SearchScreenState.SearchResults(
                        list = currentList,
                        state = SearchResultsState.Loading
                    )
                )
            }

            when (val result = searchInteractor.search(query, cursor)) {
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
                                state = SearchResultsState.Results,
                                list = currentList
                            )
                        )
                    }
                }
            }
        }
    }

    private fun collectSearch() {
        viewModelScope.launch {
            queryFlow.debounce(300L)
                .collectLatest { query ->
                    searchJob?.cancel()
                    if (query.isBlank()) {
                        return@collectLatest
                    }
                    searchRequest(query)
                }
        }
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.ChangeSearchText -> {
                if (action.search == _uiStateFlow.value.searchString) return
                currentList.clear()
                cursor = ""
                if (action.search.isNotBlank()) {
                    _uiStateFlow.update {
                        it.copy(
                            searchString = action.search,
                            state = SearchScreenState.SearchResults(
                                state = SearchResultsState.Loading,
                                list = emptyList()
                            )
                        )
                    }
                } else {
                    _uiStateFlow.update {
                        it.copy(
                            searchString = action.search,
                            state = SearchScreenState.ZeroSearch(
                                popularCities = citiesRepository.cities()
                                    .map { city -> city.name }
                            )
                        )
                    }
                }
                queryFlow.update { action.search }
            }

            is SearchAction.ActivityClicked -> {
                when (action.entity.type) {
                    ActivityEntity.ActivityType.LANDMARK -> {
                        viewModelScope.launch {
                            _uiEvent.send(SearchEvent.OnOpenAttraction(action.entity.id))
                        }
                    }

                    ActivityEntity.ActivityType.SERVICE -> {
                        viewModelScope.launch {
                            _uiEvent.send(SearchEvent.OnOpenService(action.entity.id))
                        }
                    }
                }
            }

            SearchAction.BackPressed -> {
                when (_uiStateFlow.value.state) {
                    is SearchScreenState.SearchResults -> {
                        _uiStateFlow.update {
                            it.copy(
                                searchString = "",
                                state = SearchScreenState.ZeroSearch(
                                    popularCities = citiesRepository.cities()
                                        .map { city -> city.name }
                                )
                            )
                        }
                    }

                    is SearchScreenState.ZeroSearch -> {
                        viewModelScope.launch {
                            _uiEvent.send(SearchEvent.OnBackPressed)
                        }
                    }
                }
            }
        }
    }
}
