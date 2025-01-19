package com.example.travelling.common.data

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.common.utils.Analytics
import javax.inject.Inject

class DeeplinkHandlerImpl @Inject constructor(
    private val activity: Activity,
) : DeeplinkHandler {

    override fun handleDeeplink(deeplink: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = Uri.parse(deeplink)
            }
            activity.startActivity(intent)
        } catch (throwable: Throwable) {
            Analytics.reportClientError(feature = "deeplink", throwable = throwable)
        }
    }
}