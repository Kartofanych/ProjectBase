package com.filters.impl.di

import android.content.Context
import com.example.multimodulepractice.common.di.AppContext
import javax.inject.Inject

class FiltersDependencies @Inject constructor(
    @AppContext
    val context: Context
)