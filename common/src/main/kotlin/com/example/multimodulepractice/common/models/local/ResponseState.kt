package com.example.multimodulepractice.common.models.local

sealed interface ResponseState<T> {
    class Success<T>(val data: T) : ResponseState<T>

    sealed interface Error<T>: ResponseState<T> {
        class Default<T>: Error<T>

        class OldVersion<T>: Error<T>
    }
}
