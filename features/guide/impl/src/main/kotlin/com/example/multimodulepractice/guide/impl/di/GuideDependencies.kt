package com.example.multimodulepractice.guide.impl.di

import android.content.Context
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.guide.impl.data.GuideApi
import javax.inject.Inject

class GuideDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val guideApi: GuideApi
)