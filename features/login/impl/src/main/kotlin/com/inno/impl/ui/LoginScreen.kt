package com.inno.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.login.R
import com.inno.impl.ui.composables.GuestsLoginButton
import com.inno.impl.ui.composables.Separator
import com.inno.impl.ui.composables.YandexLoginButton

@Composable
fun LoginScreen(navigateToMain: () -> Unit) {
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
                .size(120.dp)
        )

        GuestsLoginButton(navigateToMain)

        Separator()

        YandexLoginButton()
    }
}
