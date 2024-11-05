package com.start.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.login.start.impl.BuildConfig
import com.example.multimodulepractice.login.start.impl.R
import com.start.impl.ui.composables.GuestsLoginButton
import com.start.impl.ui.composables.LoginButton

@Composable
fun StartScreen(uiState: StartUiState, onAction: (StartAction) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
            )

            Box(modifier = Modifier.height(50.dp))

            Text(
                text = "Путешествуйте\nс нами",
                textAlign = TextAlign.Center,
                style = semiboldTextStyle.copy(fontSize = 24.sp, color = Color.Black)
            )

            Box(modifier = Modifier.height(65.dp))

            LoginButton(onAction)

            Box(modifier = Modifier.height(16.dp))

            GuestsLoginButton(onAction)
        }

        Text(
            text = "v${BuildConfig.VERSION_NAME}",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF778389))
        )
    }
}
