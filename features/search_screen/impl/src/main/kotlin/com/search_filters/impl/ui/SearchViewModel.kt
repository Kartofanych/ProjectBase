package com.search_filters.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.common.data.models.local.ActivityEntity
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.search_filters.api.data.domain.SearchFilterRepository
import com.search_filters.api.data.models.SearchFilters
import com.search_filters.impl.domain.SearchInteractor
import com.search_filters.impl.ui.SearchUiState.SearchResultsState
import com.search_filters.impl.ui.SearchUiState.SearchScreenState
import com.splash.api.domain.CitiesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val searchInteractor: SearchInteractor,
    private val searchFilterRepository: SearchFilterRepository
) : ViewModel() {

    private val _uiEvent = Channel<SearchEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(
        SearchUiState.empty(cities = citiesRepository.cities().map { it.name })
    )

    val uiStateFlow: StateFlow<SearchUiState>
        get() = _uiStateFlow

    private val queryFlow = MutableStateFlow("")

    private var cursor: String? = ""
    private val currentList = mutableListOf<ActivityEntity>()
    private var searchJob: Job? = null

    init {
        Analytics.reportOpenFeature(feature = "search")
        collectSearch()
        collectFilters()
    }

    private fun collectFilters() {
        viewModelScope.launch {
            searchFilterRepository.filters.filterNotNull().collect { filters ->
                clearSearchCache()
                when {
                    isZeroSearch(filters, _uiStateFlow.value.searchString) ->
                        _uiStateFlow.update {
                            it.copy(
                                state = SearchScreenState.ZeroSearch(
                                    citiesRepository.cities().map { city -> city.name })
                            )
                        }

                    else -> {
                        searchRequest(_uiStateFlow.value.searchString, filters = filters)
                    }
                }
            }
        }
    }

    private fun searchRequest(
        query: String,
        filters: SearchFilters = searchFilterRepository.zeroFilters
    ) {
        cursor?.let { currentCursor ->
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

                when (val result = searchInteractor.search(query, filters, currentCursor)) {
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
                searchJob = null
            }
        }
    }

    private fun collectSearch() {
        viewModelScope.launch {
            queryFlow.debounce(300L)
                .collectLatest { query ->
                    searchJob?.cancel()
                    if (isZeroSearch(searchFilterRepository.filters.value, query)) {
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
                clearSearchCache()
                if (isZeroSearch(searchFilterRepository.filters.value, action.search)) {
                    _uiStateFlow.update {
                        it.copy(
                            searchString = action.search,
                            state = SearchScreenState.ZeroSearch(
                                popularCities = citiesRepository.cities()
                                    .map { city -> city.name }
                            )
                        )
                    }
                } else {
                    _uiStateFlow.update {
                        it.copy(
                            searchString = action.search,
                            state = SearchScreenState.SearchResults(
                                state = SearchResultsState.Loading,
                                list = emptyList()
                            )
                        )
                    }
                }
                queryFlow.update { action.search }
            }

            is SearchAction.ActivityClicked -> {
                when (action.entity.type) {
                    ActivityEntity.ActivityType.LANDMARK -> {
                        Analytics.reportFeatureAction(feature = "search", action = "attraction_tap")
                        viewModelScope.launch {
                            _uiEvent.send(SearchEvent.OnOpenAttraction(action.entity.id))
                        }
                    }

                    ActivityEntity.ActivityType.SERVICE -> {
                        Analytics.reportFeatureAction(feature = "search", action = "service_tap")
                        viewModelScope.launch {
                            _uiEvent.send(SearchEvent.OnOpenService(action.entity.id))
                        }
                    }
                }
            }

            SearchAction.BackPressed -> {
                when (_uiStateFlow.value.state) {
                    is SearchScreenState.SearchResults -> {
                        searchJob?.cancel()
                        searchFilterRepository.updateFilters(searchFilterRepository.zeroFilters)
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

            SearchAction.OpenFilters -> {
                Analytics.reportFeatureAction(feature = "search", action = "filters_tap")
                viewModelScope.launch {
                    _uiEvent.send(SearchEvent.OnOpenFilters)
                }
            }

            is SearchAction.OnScrollAction -> {
                if (action.firstVisibleItem + 8 >= currentList.size && searchJob == null && cursor != null) {
                    Analytics.reportFeatureAction(feature = "search", action = "next_page")
                    searchRequest(
                        _uiStateFlow.value.searchString,
                        searchFilterRepository.filters.value
                    )
                }
            }

            SearchAction.OnDispose -> {

            }
        }
    }

    private fun clearSearchCache() {
        currentList.clear()
        cursor = ""
    }

    private fun isZeroSearch(filters: SearchFilters, searchString: String): Boolean {
        return filters == searchFilterRepository.zeroFilters && searchString.isBlank()
    }
}
