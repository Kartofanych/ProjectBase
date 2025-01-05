package com.start.impl.ui.composables

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.login.process.impl.R
import com.example.travelling.common.theme.regularTextStyle
import com.start.impl.ui.ProcessAction
import com.start.impl.ui.ProcessUiState

@Composable
fun InputField(uiState: ProcessUiState, onAction: (ProcessAction) -> Unit) {
    var textField by remember {
        mutableStateOf(TextFieldValue(text = ""))
    }
    val focusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.height(54.dp)) {
        BasicTextField(
            value = textField,
            onValueChange = {
                textField = it
                onAction(ProcessAction.ChangeMailText(it.text))
            },
            singleLine = true,
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .height(44.dp)
                .background(Color(0x1A675151), RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(250.dp)
                        .align(Alignment.CenterStart),
                    text = when {
                        textField.text.isBlank() -> "Укажите почту"
                        else -> textField.text
                    },
                    style = regularTextStyle.copy(
                        color = when {
                            textField.text.isBlank() -> Color(0xFF838383)
                            else -> Color.Black
                        },
                        fontSize = 15.sp
                    ),
                    maxLines = 1
                )

                androidx.compose.animation.AnimatedVisibility(
                    enter = fadeIn(),
                    exit = fadeOut(),
                    visible = textField.text.isNotBlank(),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .clickable {
                                textField = textField.copy(text = "")
                                onAction(ProcessAction.ChangeMailText(""))
                            },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = Color(0xFF778389)
                    )
                }
            }
        }
        androidx.compose.animation.AnimatedVisibility(
            enter = fadeIn(),
            exit = fadeOut(),
            visible = (uiState.loginState is ProcessUiState.LoginState.EmailState) && uiState.loginState.error != null,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 3.dp),
                text = "Проверьте правильность почты",
                style = regularTextStyle.copy(color = Color(0xFFFD5539), fontSize = 10.sp)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    DisposableEffect(Unit) {
        textField = textField.copy(
            selection = TextRange(textField.text.length)
        )
        onDispose { }
    }

    BackHandler {
        if (textField.text.isNotBlank()) {
            textField = textField.copy(text = "")
        } else {
            onAction(ProcessAction.OnClose)
        }
    }
}