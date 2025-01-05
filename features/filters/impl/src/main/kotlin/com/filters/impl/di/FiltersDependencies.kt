package com.filters.impl.di

import android.content.Context
import com.example.travelling.common.di.AppContext
import com.filters.api.data.FiltersRepository
import javax.inject.Inject

class FiltersDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val filtersRepository: FiltersRepository
)