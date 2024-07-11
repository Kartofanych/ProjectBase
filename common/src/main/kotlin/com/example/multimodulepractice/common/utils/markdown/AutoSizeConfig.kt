package com.example.multimodulepractice.common.utils.markdown

import android.util.TypedValue

data class AutoSizeConfig(
    val autoSizeMinTextSize: Int,
    val autoSizeMaxTextSize: Int,
    val autoSizeStepGranularity: Int,
    val unit: Int = TypedValue.COMPLEX_UNIT_SP,
)