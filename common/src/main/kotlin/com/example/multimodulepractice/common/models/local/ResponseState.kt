package com.example.multimodulepractice.common.models.local

import java.lang.Exception

sealed interface ResponseState<T> {

    class Error<T>(val data: T) : ResponseState<T>

    class Success<T>(val data: T) : ResponseState<T>

}