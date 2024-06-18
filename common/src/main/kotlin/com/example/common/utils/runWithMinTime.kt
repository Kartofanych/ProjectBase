package com.example.common.utils

import kotlinx.coroutines.delay

suspend fun <T> runWithMinTime(
    longRunningTask: suspend () -> T,
    minTime: Long = 1000L
): T {
    val startTime = System.currentTimeMillis()
    val result = longRunningTask()

    val elapsedTime = System.currentTimeMillis() - startTime
    val remainingTime = minTime - elapsedTime

    if (remainingTime > 0) {
        delay(remainingTime)
    }

    return result
}
