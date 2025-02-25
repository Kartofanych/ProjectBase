package com.example.travelling.main.impl.data.repositories

import com.example.travelling.common.di.AppScope
import com.example.travelling.main.impl.domain.MapScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@AppScope
class MapScreenRepositoryImpl @Inject constructor() : MapScreenRepository {

    private val _isMapOpen = MutableStateFlow(false)
    override val isMapOpen = _isMapOpen.asStateFlow()

    override fun updateMapVisibility(isVisible: Boolean) {
        _isMapOpen.update { isVisible }
    }
}
