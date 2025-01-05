package com.example.travelling.guide.impl.di

import android.content.Context
import com.example.travelling.common.di.AppContext
import com.example.travelling.guide.impl.data.GuideApi
import javax.inject.Inject

class GuideDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val guideApi: GuideApi
)