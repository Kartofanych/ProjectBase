package com.example.multimodulepractice.login.impl.data

import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {

    @GET("info")
    suspend fun getInfo(@Header("Authorization") header: String): YandexAuthInfoDto

}