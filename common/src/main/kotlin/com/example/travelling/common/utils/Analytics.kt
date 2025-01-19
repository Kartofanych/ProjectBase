package com.example.travelling.common.utils

import io.appmetrica.analytics.AppMetrica
import retrofit2.HttpException

object Analytics {

    fun reportOpenFeature(feature: String, params: Map<String, Any> = emptyMap()) {
        val eventName = OPEN_FEATURE + feature
        reportEvent(eventName = eventName, params = params)
    }

    fun reportFeatureAction(feature: String, action: String) {
        reportEvent(eventName = feature, params = mapOf("action" to action))
    }

    fun reportNetworkError(route: String, throwable: Throwable) {
        val eventName = NETWORK_ERROR + route
        val code = (throwable as? HttpException)?.code() ?: "Client Error"
        val message = throwable.message ?: "Empty message"
        val params = mapOf(ERROR_CODE to code, ERROR_MESSAGE to message)
        reportEvent(eventName = eventName, params = params)
    }

    fun reportClientError(feature: String, throwable: Throwable) {
        val eventName = CLIENT_ERROR + feature
        val message = throwable.message ?: "Empty message"
        val params = mapOf(ERROR_MESSAGE to message)
        reportEvent(eventName = eventName, params = params)
    }

    fun reportNetworkSuccess(route: String, millis: Long) {
        val eventName = SUCCESS + route
        val seconds = String.format("%.1f", millis / 1000f)
        val params = mapOf(TIME to seconds)
        reportEvent(eventName = eventName, params = params)
    }

    private fun reportEvent(eventName: String, params: Map<String, Any>) {
        AppMetrica.reportEvent(eventName, params)
    }

    private const val NETWORK_ERROR = "network.error."
    private const val SUCCESS = "network.success."
    private const val CLIENT_ERROR = "client.error."
    private const val OPEN_FEATURE = "open."
    private const val ERROR_CODE = "code"
    private const val ERROR_MESSAGE = "message"
    private const val TIME = "time"
}