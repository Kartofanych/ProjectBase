package com.attraction.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.ui.AttractionAction
import com.attraction.impl.ui.ReviewModalState
import com.example.multimodulepractice.attraction.impl.R
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.composables.DefaultError
import com.example.travelling.common.composables.DefaultLoading
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewModal(
    uiState: ReviewModalState,
    onAction: (AttractionAction) -> Unit,
    bottomPadding: Dp
) {
    if (uiState !is ReviewModalState.Hidden) {
        var textField by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }

        ModalBottomSheet(
            dragHandle = null,
            onDismissRequest = {
                onAction(AttractionAction.ChangeReviewModalVisibility(false))
            },
            containerColor = Color.White,
        ) {

            when (uiState) {
                is ReviewModalState.Default -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(bottom = bottomPadding + 10.dp),
                    ) {
                        Spacer(Modifier.height(18.dp))

                        Text(
                            text = "Оставьте отзыв",
                            style = semiboldTextStyle.copy(fontSize = 24.sp, color = Color.Black)
                        )

                        Spacer(Modifier.height(24.dp))

                        Row {
                            (1..5).forEach { i ->
                                val isActive = i <= uiState.starsCount
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_star),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(35.dp)
                                        .touchAction {
                                            onAction(AttractionAction.ChangeReviewStars(i))
                                        },
                                    tint = if (isActive) Color(0xFF74A3FF) else Color(0x3B959595)
                                )
                                if (i != 5) {
                                    Spacer(modifier = Modifier.width(3.dp))
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        BasicTextField(
                            value = textField,
                            onValueChange = {
                                textField = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            decorationBox = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 160.dp)
                                        .background(
                                            color = Color(0xFFF2F3F5),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(vertical = 14.dp, horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = when {
                                            textField.isEmpty() -> "Напишите, что вы думаете о данном месте"
                                            else -> textField
                                        },
                                        style = regularTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = when {
                                                textField.isEmpty() -> Color(0xFF535353)
                                                else -> Color(0xFF262525)
                                            }
                                        )
                                    )
                                }
                            }
                        )

                        Spacer(Modifier.height(40.dp))

                        DefaultButton(
                            backgroundColor = Color(0xFF404040),
                            onClick = {
                                onAction(AttractionAction.SendReview(textField))
                            },
                            modifier = Modifier
                                .width(300.dp)
                                .height(40.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Отправить отзыв",
                                style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.White)
                            )
                        }
                    }
                }

                ReviewModalState.Loading -> DefaultLoading(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                )

                else -> DefaultError(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    onReload = { onAction(AttractionAction.SendReview(textField)) }
                )
            }
        }
    }
}
