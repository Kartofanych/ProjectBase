package com.inno.impl.data.interactors

import android.util.Log
import com.inno.impl.data.network.MainApi
import com.inno.impl.data.network.models.request.GeoPointDto
import com.inno.impl.data.network.models.request.StartInfoRequest
import com.inno.impl.data.network.models.response.StartInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MapInteractor @Inject constructor(
    private val api: MainApi
) {

    suspend fun getMapInfo() {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getStartInfo(
                    StartInfoRequest(
                        GeoPointDto(
                            1f, 1f
                        )
                    )
                )
                response.enqueue(object : Callback<StartInfoResponse> {
                    override fun onResponse(
                        call: Call<StartInfoResponse>,
                        response: Response<StartInfoResponse>
                    ) {
                        val body = response.body()
                        Log.d("121212", body.toString())
                    }

                    override fun onFailure(call: Call<StartInfoResponse>, t: Throwable) {
                        Log.d("121212", t.message.toString())
                    }

                })
            } catch (exception: Exception) {

            }
        }
    }

}