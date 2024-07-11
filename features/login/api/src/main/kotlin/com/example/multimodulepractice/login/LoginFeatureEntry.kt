package com.example.multimodulepractice.login

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class LoginFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "login"
    }
}