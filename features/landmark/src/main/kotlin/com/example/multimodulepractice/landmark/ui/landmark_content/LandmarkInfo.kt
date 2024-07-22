package com.example.multimodulepractice.landmark.ui.landmark_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.landmark.data.LandmarkResponse

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandmarkInfo(landmark: LandmarkResponse, onOpenAudioGuide: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(bottom = 35.dp)
    ) {

        Text(
            text = landmark.name,
            fontWeight = FontWeight.Bold,
            style = semiboldTextStyle.copy(fontSize = 16.sp)
        )

        Text(
            text = landmark.address,
            fontSize = 10.sp,
            color = Color.Gray,
            style = mediumTextStyle
        )

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (category in landmark.categories) {
                Chip(text = category.name, color = category.color)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Описание",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = semiboldTextStyle
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = landmark.info,
            fontSize = 15.sp,
            style = mediumTextStyle
        )

        Spacer(modifier = Modifier.height(11.dp))

        if (landmark.audioGuides.isNotEmpty()) {
            Text(
                text = "Аудио гид",
                fontSize = 16.sp,
                style = semiboldTextStyle
            )

            Spacer(modifier = Modifier.height(10.dp))

            AudioGidItemLayout(audioGid = landmark.audioGuides[0], onOpenAudioGuide = onOpenAudioGuide)

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
