package com.travelling.api

interface AppConfig {
    fun isProduction(): Boolean
    fun updateMode(isProduction: Boolean)
}