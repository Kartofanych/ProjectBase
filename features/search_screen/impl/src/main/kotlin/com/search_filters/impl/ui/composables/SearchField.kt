package com.search_filters.impl.ui.composables

import androidx.activity.compose.BackHandler
import androidx.compose.animation.expandIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.search_screen.impl.R
import com.search_filters.impl.ui.SearchAction
import com.search_filters.impl.ui.SearchUiState

@Composable
fun SearchField(uiState: SearchUiState, onAction: (SearchAction) -> Unit) {
    var textField by remember {
        mutableStateOf(TextFieldValue(text = uiState.searchString))
    }
    val focusRequester = remember { FocusRequester() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(Color.White)
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = textField,
            onValueChange = {
                textField = it
                onAction(SearchAction.ChangeSearchText(it.text))
            },
            singleLine = true,
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(308.dp)
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
                        uiState.searchString.isBlank() -> "Например, Кремль"
                        else -> uiState.searchString
                    },
                    style = regularTextStyle.copy(
                        color = when {
                            uiState.searchString.isBlank() -> Color(0xFF838383)
                            else -> Color.Black
                        },
                        fontSize = 15.sp
                    ),
                    maxLines = 1
                )

                androidx.compose.animation.AnimatedVisibility(
                    enter = expandIn {
                        IntSize(18, 18)
                    },
                    visible = uiState.searchString.isNotBlank(),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                textField = textField.copy(text = "")
                                onAction(SearchAction.ChangeSearchText(""))
                            },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null
                    )
                }
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(30.dp)
                .clip(CircleShape)
                .clickable {
                    onAction(SearchAction.OpenFilters)
                },
            painter = painterResource(id = R.drawable.ic_filters),
            tint = Color(0xFF74A3FF),
            contentDescription = null
        )
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
        textField = textField.copy(text = "")
        onAction(SearchAction.BackPressed)
    }
}