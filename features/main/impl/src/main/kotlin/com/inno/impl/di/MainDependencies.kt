package com.inno.impl.di

import android.content.Context
import com.example.common.di.AppContext
import javax.inject.Inject

class MainDependencies @Inject constructor(
    @AppContext
    val context: Context
)
