package com.start.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.mediumTextStyle
import com.start.impl.ui.ProcessAction
import com.start.impl.ui.ProcessUiState

@Composable
fun CodeInput(uiState: ProcessUiState, onAction: (ProcessAction) -> Unit) {
    var textField by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = textField,
        onValueChange = {
            if (it.length <= 4) {
                textField = it
                onAction(ProcessAction.ChangeCodeText(textField))
            }
        },
        enabled = uiState.loginState is ProcessUiState.LoginState.CodeState
                && uiState.loginState.state != ProcessUiState.LoginState.CodeState.CodeScreenState.LOADING,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        decorationBox = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(4) { index ->
                    val char = when {
                        index >= textField.length -> ""
                        else -> textField[index].toString()
                    }
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(84.dp)
                            .background(
                                Color(0xFFE5EEFF),
                                RoundedCornerShape(12.dp)
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                textField = textField.substring(0, index)
                                focusRequester.requestFocus()
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = char,
                            style = mediumTextStyle.copy(
                                color = Color(0xFF74A3FF),
                                fontSize = 48.sp
                            ),
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
