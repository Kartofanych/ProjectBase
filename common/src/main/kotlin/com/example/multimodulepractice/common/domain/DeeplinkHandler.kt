package com.example.multimodulepractice.common.domain

import android.content.Intent

interface DeeplinkHandler {
    fun handleIntent(intent: Intent)
}