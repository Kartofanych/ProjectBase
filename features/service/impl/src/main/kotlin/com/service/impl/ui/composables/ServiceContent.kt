package com.service.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ServiceAction

@Composable
fun ServiceContent(service: Service, onAction: (ServiceAction) -> Unit) {

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {

        ServiceToolbar(service = service, scrollState = scrollState, onAction = onAction)

        ServiceBody(service = service, scrollState = scrollState)

        ServiceFooter(
            service = service,
            modifier = Modifier.align(Alignment.BottomCenter),
            onAction = onAction
        )
    }
}
