package com.example.travelling.common.utils

import kotlin.coroutines.cancellation.CancellationException

inline fun <T> networkCall(
    run: () -> T,
    catch: (Throwable) -> T,
): T {
    return try {
        run()
    } catch (throwable: Throwable) {
        if (throwable is CancellationException) {
            throw throwable
        }
        catch(throwable)
    }
}