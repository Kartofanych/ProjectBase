package com.example.multimodulepractice.login.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.rememberSwipeableState
import com.example.multimodulepractice.login.impl.R
import com.example.multimodulepractice.login.impl.ui.composables.GuestsLoginButton
import com.example.multimodulepractice.login.impl.ui.composables.Separator
import com.example.multimodulepractice.login.impl.ui.composables.YandexLoginButton
import com.example.multimodulepractice.login.impl.ui.models.ConfirmationState
import kotlinx.coroutines.launch

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel, navigateToMain: () -> Unit) {

    val scope = rememberCoroutineScope()

    val swipeableState = rememberSwipeableState(initialValue = ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) {
        190.dp.toPx()
    }
    val progress = remember {
        derivedStateOf {
            if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
        }
    }

    viewModel.HandleProgress(progress = progress.value)

    val events = object : LoginScreenEvents {
        override fun loginCancelled() {
            scope.launch {
                swipeableState.animateTo(ConfirmationState.DEFAULT)
            }
        }

        override fun loginSuccess() {
            scope.launch {
                swipeableState.animateTo(ConfirmationState.DEFAULT)
                navigateToMain()
            }
        }
    }

    viewModel.setEvents(events)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.icon_yandex),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 100.dp)
                .size(140.dp)
        )

        GuestsLoginButton(navigateToMain)

        Separator()

        YandexLoginButton(swipeableState, progress)
    }
}

interface LoginScreenEvents {

    fun loginCancelled()

    fun loginSuccess()

}