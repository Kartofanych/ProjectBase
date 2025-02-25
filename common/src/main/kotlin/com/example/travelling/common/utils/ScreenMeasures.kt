package com.example.travelling.common.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Context.screenHeightPx(): Int {
    val manager = (getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    val point = Point()
    manager.defaultDisplay.getRealSize(point)
    return point.y
}

fun Context.screenWidthPx(): Int {
    val manager = (getSystemService(Context.WINDOW_SERVICE) as WindowManager)
    val point = Point()
    manager.defaultDisplay.getRealSize(point)
    return point.x
}

fun Context.screenHeightDp(): Dp {
    return pxToDp(screenHeightPx().toFloat()).dp
}

fun Context.screenWidthDp(): Dp {
    return pxToDp(screenWidthPx().toFloat()).dp
}

fun Context.dpToPx(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.pxToDp(px: Float): Float {
    return px / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun getStatusBarHeight(): Int {
    val resources = Resources.getSystem()
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}
