package com.example.multimodulepractice.main.impl.domain

import kotlinx.coroutines.flow.StateFlow

interface MapScreenRepository {

    val isMapOpen: StateFlow<Boolean>

    fun updateMapVisibility(isVisible: Boolean)
}