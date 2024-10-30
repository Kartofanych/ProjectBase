package com.example.multimodulepractice.common.data

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.multimodulepractice.common.domain.DeeplinkHandler
import javax.inject.Inject

class DeeplinkHandlerImpl @Inject constructor(
    private val activity: Activity,
) : DeeplinkHandler {

    override fun handleIntent(intent: Intent) {
        try {
            activity.startActivity(intent)
        } catch (e: Exception) {
            Log.e("$this", e.toString())
        }
    }
}