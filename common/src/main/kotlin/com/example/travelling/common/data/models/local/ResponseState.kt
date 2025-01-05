package com.example.travelling.common.data.models.local

sealed interface ResponseState<T> {
    class Success<T>(val data: T) : ResponseState<T>

    sealed interface Error<T>: ResponseState<T> {
        class Default<T>: Error<T>

        // 426
        class OldVersion<T>: Error<T>

        // 401
        class Unauthorized<T> : Error<T>

        // 498
        class BadCode<T> : Error<T>
    }
}
