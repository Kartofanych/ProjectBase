package com.example.travelling.main.impl.ui.map

import com.example.travelling.common.data.models.local.City
import kotlinx.coroutines.Job

data class MapInfoJob(
    val job: Job? = null,
    val city: City? = null
)
