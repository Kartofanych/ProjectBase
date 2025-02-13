package com.service.impl.ui

import com.service.impl.data.models.local.Service

data class ServiceUiState(
    val state: DataState,
    val isScheduleVisible: Boolean = false,
    val isAuthorized: Boolean = false,
    val isCallBottomSheetVisible: Boolean = false,
) {

    sealed interface DataState {

        data object Loading : DataState

        data object Error : DataState

        data class Content(val service: Service) : DataState
    }
}
