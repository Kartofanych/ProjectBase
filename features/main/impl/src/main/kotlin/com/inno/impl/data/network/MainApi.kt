package com.inno.impl.data.network

import com.inno.impl.data.network.models.request.StartInfoRequest
import com.inno.impl.data.network.models.response.StartInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("get_start_info")
    fun getStartInfo(@Body body: StartInfoRequest): Call<StartInfoResponse>

}