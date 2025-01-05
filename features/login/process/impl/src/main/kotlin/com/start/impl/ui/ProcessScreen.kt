package com.start.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.login.process.impl.R
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.start.impl.ui.ProcessUiState.LoginState.CodeState.CodeScreenState
import com.start.impl.ui.composables.CodeInput
import com.start.impl.ui.composables.InputField
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProcessScreen(uiState: ProcessUiState, onAction: (ProcessAction) -> Unit) {

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.imePadding(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = (uiState.loginState is ProcessUiState.LoginState.EmailState)
                        && uiState.currentText.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                    onClick = { onAction(ProcessAction.NextPressed) },
                    modifier = Modifier.size(56.dp),
                    containerColor = Color(0xFF74A3FF),
                    shape = CircleShape,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                            .size(25.dp),
                        tint = Color.White
                    )
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.padding(top = it.calculateTopPadding())) {

                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(start = 16.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            onAction(ProcessAction.OnClose)
                        }
                        .padding(8.dp)
                )

                Text(
                    text = "Вход",
                    style = semiboldTextStyle.copy(fontSize = 26.sp, color = Color.Black),
                    modifier = Modifier.padding(start = 26.dp, top = 16.dp)
                )
            }

            AnimatedVisibility(
                modifier = Modifier.padding(top = it.calculateTopPadding() + 135.dp),
                visible = uiState.loginState is ProcessUiState.LoginState.EmailState,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { -it }
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 26.dp)
                ) {

                    InputField(uiState, onAction)

                    Box(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        text = "Продолжая, я соглашаюсь с Пользовательским соглашением, а также с обработкой моей персональной информации на условиях Политики конфиденциальности",
                        style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF778389))
                    )
                }
            }

            AnimatedVisibility(
                visible = uiState.loginState is ProcessUiState.LoginState.CodeState,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it },
            ) {
                if (uiState.loginState is ProcessUiState.LoginState.CodeState) {
                    var ticks by remember { mutableIntStateOf(59) }
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(1.seconds)
                            if (ticks > 0) {
                                ticks--
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(top = it.calculateTopPadding() + 125.dp)
                            .padding(horizontal = 26.dp)
                    ) {
                        Text(
                            text = "Отправили на вашу почту письмо с кодом",
                            style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.Black)
                        )

                        Box(modifier = Modifier.height(32.dp))

                        CodeInput(uiState, onAction)


                        if (uiState.loginState.state == CodeScreenState.ERROR) {
                            Text(
                                text = "Неверный код",
                                style = regularTextStyle.copy(
                                    fontSize = 14.sp,
                                    color = Color(0xFFFD5539)
                                )
                            )
                        }

                        Box(modifier = Modifier.height(32.dp))

                        Text(
                            text = if (ticks > 0) "Запросить повторно можно через $ticks секунд" else "Запросить еще",
                            style = regularTextStyle.copy(
                                fontSize = 14.sp,
                                color = if (ticks > 0) Color(0xFF737F89) else Color(0xFF74A3FF)
                            ),
                            modifier = Modifier.clickable(
                                enabled = ticks == 0,
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                ticks = 59
                                onAction(ProcessAction.ResendCode)
                            }
                        )
                    }
                }

                if (uiState.loginState is ProcessUiState.LoginState.CodeState && uiState.loginState.state == CodeScreenState.LOADING) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(0x33000000)),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFF74A3FF),
                            strokeWidth = 4.dp,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}