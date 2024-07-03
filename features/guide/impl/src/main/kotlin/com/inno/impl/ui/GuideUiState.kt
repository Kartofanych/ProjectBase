package com.inno.impl.ui

data class GuideUiState(
    val currentPage: Int,
    val state: DataState
) {

    sealed interface DataState {
        object Error : DataState
        object Loading : DataState
        object Content : DataState
    }

    companion object {
        val EMPTY = GuideUiState(
            currentPage = 0,
            state = DataState.Loading
        )
    }

}