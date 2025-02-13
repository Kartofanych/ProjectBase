package com.search_filters.impl.ui.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.search_screen.impl.R
import com.example.travelling.common.theme.regularTextStyle
import com.search_filters.impl.ui.SearchAction
import com.search_filters.impl.ui.SearchUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    uiState: SearchUiState,
    onAction: (SearchAction) -> Unit,
    textField: MutableState<TextFieldValue>
) {
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(Color.White)
            .padding(start = 18.dp, end = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = textField.value,
            onValueChange = {
                textField.value = it
                onAction(SearchAction.ChangeSearchText(it.text))
            },
            singleLine = true,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Color(0xFF74A3FF)),
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(308.dp)
                .height(44.dp)
                .background(Color(0x1A675151), RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp),
        ) {

            TextFieldDefaults.DecorationBox(
                value = textField.value.text,
                enabled = true,
                innerTextField = {
                    if (textField.value.text.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(250.dp)
                                .align(Alignment.CenterStart),
                            text = "Где поесть",
                            style = regularTextStyle.copy(
                                color = Color(0xFF838383),
                                fontSize = 13.sp
                            ),
                            maxLines = 1
                        )
                    } else {
                        it()
                    }
                },
                interactionSource = interactionSource,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(16),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(0xFF74A3FF),
                    focusedPlaceholderColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                contentPadding = PaddingValues(vertical = 12.dp)
            )
            /*  Box(modifier = Modifier.fillMaxSize()) {
                  Text(
                      modifier = Modifier
                          .wrapContentHeight()
                          .width(250.dp)
                          .align(Alignment.CenterStart),
                      text = when {
                          uiState.searchString.isBlank() -> "Где поесть"
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
                              .touchAction {
                                  onAction(SearchAction.ChangeSearchText(""))
                              },
                          painter = painterResource(id = R.drawable.ic_close),
                          contentDescription = null
                      )
                  }
              }*/
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(50.dp)
                .clip(CircleShape)
                .clickable {
                    onAction(SearchAction.OpenFilters)
                }
                .padding(10.dp),
            painter = painterResource(id = R.drawable.ic_filters),
            tint = Color(0xFF74A3FF),
            contentDescription = null
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler {
        textField.value = textField.value.copy(text = "")
        onAction(SearchAction.BackPressed)
    }
}