package com.example.travelling.main.impl.domain

import kotlinx.coroutines.flow.StateFlow

interface MapScreenRepository {

    val isMapOpen: StateFlow<Boolean>

    fun updateMapVisibility(isVisible: Boolean)
}