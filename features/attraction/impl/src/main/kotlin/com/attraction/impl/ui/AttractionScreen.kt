package com.attraction.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import com.attraction.impl.ui.composables.LandmarkContent
import com.example.multimodulepractice.attraction.impl.R
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle

@Composable
fun AttractionScreen(uiState: AttractionUiState, onAction: (AttractionAction) -> Unit) {

    when (uiState) {
        is AttractionUiState.Content -> {
            LandmarkContent(
                landmark = uiState.landmark,
                onAction = onAction
            )
        }

        is AttractionUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .clickable {
                        onAction(AttractionAction.RecallAttraction)
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

        AttractionUiState.Loading -> {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }
    }
}