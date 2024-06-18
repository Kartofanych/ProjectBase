package com.example.common.models.local

sealed interface ResponseState<T> {

    class Error<T> : ResponseState<T>

    class Success<T>(val data: T) : ResponseState<T>

}