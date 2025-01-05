package com.example.travelling.common.domain

import android.content.Intent

interface DeeplinkHandler {
    fun handleIntent(intent: Intent)
}