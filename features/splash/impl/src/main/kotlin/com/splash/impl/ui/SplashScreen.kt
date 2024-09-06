package com.splash.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.splash.impl.R

@Composable
fun SplashScreen(
    type: ErrorType,
    updateAction: () -> Unit,
    closeAction: () -> Unit,
) {
    when(type) {
        ErrorType.INTERNET_ERROR -> SplashScreenInternetVersion(closeAction)
        ErrorType.APP_VERSION_ERROR -> SplashScreenAppVersion(updateAction, closeAction)
    }
}

@Composable
fun SplashScreenInternetVersion(
    closeAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {


        Box(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.TopEnd)
                .size(40.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable { closeAction() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 15.dp)
                    .align(Alignment.CenterStart),
                text = "Проблемы с интернетом",
                style = semiboldTextStyle.copy(fontSize = 26.sp, color = Color(0xFF000000))
            )

            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.round_close_24),
                contentDescription = null,
                tint = Color(0xFF000000)
            )
        }
    }
}

@Composable
fun SplashScreenAppVersion(
    updateAction: () -> Unit,
    closeAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.TopEnd)
                .size(40.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable { closeAction() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.round_close_24),
                contentDescription = null,
                tint = Color(0xFF000000)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp, bottom = 80.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 15.dp),
                text = "Обновите ваше приложение до последней версии",
                style = semiboldTextStyle.copy(fontSize = 26.sp, color = Color(0xFF000000))
            )
            Text(
                modifier = Modifier
                    .width(330.dp)
                    .padding(top = 15.dp),
                text = "Новая версия TimeToTravel уже доступна. Пожалуйста, обновитесь до последней версии, чтобы наслаждаться новыми функциями",
                style = regularTextStyle.copy(fontSize = 15.sp, color = Color(0xFF000000))
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .height(45.dp)
                .width(225.dp)
                .shadow(10.dp)
                .background(color = Color(0xFF74A3FF), shape = CircleShape)
                .clip(CircleShape)
                .clickable { updateAction() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Обновить",
                style = semiboldTextStyle.copy(fontSize = 18.sp, color = Color.White)
            )
        }
    }
}