package com.example.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.api.models.AuthInfo
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthScreen(authInfoFlow: Flow<AuthInfo>) {
    val authInfo = authInfoFlow.collectAsState(AuthInfo(id = "Waiting for update"))

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier,
            text = authInfo.value.id
        )
    }
}