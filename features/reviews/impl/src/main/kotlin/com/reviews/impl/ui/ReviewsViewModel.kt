package com.reviews.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.data.models.local.Review
import com.example.travelling.common.utils.Analytics
import com.reviews.impl.data.ReviewsInteractor
import com.reviews.impl.di.ReviewsComponent
import com.reviews.impl.di.ReviewsScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ReviewsScope
class ReviewsViewModel @Inject constructor(
    private val interactor: ReviewsInteractor,
    private val data: ReviewsComponent.ReviewsData,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<ReviewsEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiStateFlow = MutableStateFlow<ReviewsUiState>(ReviewsUiState.Loading)
    val uiStateFlow: StateFlow<ReviewsUiState>
        get() = _uiStateFlow

    private var paginationJob: Job? = null
    private var cursor: String? = null

    private val currentList = mutableListOf<Review>()

    init {
        Analytics.reportOpenFeature(feature = "reviews")
        onReload()
    }

    private fun loadPage() {
        currentList.clear()
        _uiStateFlow.value = ReviewsUiState.Loading
        viewModelScope.launch {
            when (val result = interactor.loadReviewsPage(data.id)) {
                is ResponseState.Error -> _uiStateFlow.value = ReviewsUiState.Error

                is ResponseState.Success -> {
                    cursor = result.data.cursor
                    with(result.data) {
                        currentList.addAll(items)
                        _uiStateFlow.update {
                            ReviewsUiState.Content(
                                ratingBlock = ratingBlock,
                                reviews = currentList,
                                title = title,
                                ratings = rateList,
                                loading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadNextPage() {
        val notnullCursor = cursor ?: return
        val state = uiStateFlow.value as? ReviewsUiState.Content ?: return
        if (state.error) return
        _uiStateFlow.update { state.copy(loading = true, error = false) }
        paginationJob = viewModelScope.launch {
            when (val result = interactor.loadReviews(data.id, notnullCursor)) {
                is ResponseState.Error -> _uiStateFlow.update {
                    state.copy(
                        reviews = currentList,
                        loading = false,
                        error = true,
                    )
                }

                is ResponseState.Success -> {
                    val items = result.data.items
                    cursor = result.data.cursor
                    currentList.addAll(items)
                    _uiStateFlow.update {
                        state.copy(
                            reviews = currentList,
                            loading = false,
                        )
                    }
                }
            }
            paginationJob = null
        }
    }

    private fun onPagination(firstVisibleItem: Int) {
        if (firstVisibleItem + 8 >= currentList.size && paginationJob == null && _uiStateFlow.value is ReviewsUiState.Content) {
            Analytics.reportFeatureAction(feature = "reviews", action = "next_page")
            loadNextPage()
        }
    }

    private fun onReload() {
        when (val state = _uiStateFlow.value) {
            is ReviewsUiState.Content -> {
                _uiStateFlow.update { state.copy(error = false) }
                loadNextPage()
            }

            else -> loadPage()
        }
    }

    fun onAction(action: ReviewsAction) {
        when (action) {
            ReviewsAction.OnBackPressed -> _uiEvent.tryEmit(ReviewsEvent.OnBack)
            ReviewsAction.OnReload -> onReload()
            is ReviewsAction.OnScrollAction -> onPagination(action.firstVisibleItem)
        }
    }
}