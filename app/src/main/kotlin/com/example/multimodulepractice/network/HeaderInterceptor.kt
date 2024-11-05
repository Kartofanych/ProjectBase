package com.example.multimodulepractice.network

//TODO to network module
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.auth.models.AuthInfo
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Named("Header")
class HeaderInterceptor @Inject constructor(
    private val authInfoManager: AuthInfoManager
) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        (authInfoManager.authInfo() as? AuthInfo.User)?.token?.let {
            requestBuilder.addHeader("traveling-auth", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}