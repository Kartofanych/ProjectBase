package com.example.multimodulepractice.main.impl.ui.compose_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.landmark.ui.Landmark
import com.example.multimodulepractice.landmark.ui.Landmark.LandmarkState
import com.example.multimodulepractice.landmark.ui.landmark_content.LandmarkContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    recallLandmark: () -> Unit,
    onOpenGuide: () -> Unit,
    onOpenAudioGuide: () -> Unit,
    landmark: Landmark
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        dragHandle = null
    ) {

        when (val state = landmark.state) {
            is LandmarkState.Content -> {
                LandmarkContent(
                    landmark = state.landmark,
                    sheetState = sheetState,
                    onOpenGuide = onOpenGuide,
                    onOpenAudioGuide = onOpenAudioGuide
                )
            }

            is LandmarkState.Error -> {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            recallLandmark()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reload),
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Что-то с интернетом",
                            style = semiboldTextStyle.copy(color = Color.Red),
                            maxLines = 1,
                            softWrap = false
                        )
                        Text(
                            text = "Нажмите чтобы перезагрузить",
                            style = mediumTextStyle.copy(
                                fontSize = 10.sp, color = Color.Red
                            ),
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }

            LandmarkState.Loading -> {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF47D88D),
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }

}