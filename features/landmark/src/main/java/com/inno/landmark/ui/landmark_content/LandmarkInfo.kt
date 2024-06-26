package com.inno.landmark.ui.landmark_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.landmark.R
import com.inno.landmark.data.LandmarkResponse

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandmarkInfo(landmark: LandmarkResponse) {
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

            AudioGidItemLayout(audioGid = landmark.audioGuides[0], onClick = {})
        }

        Spacer(modifier = Modifier.height(41.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 162.dp, height = 42.dp)
                .background(
                    color = Color(0xFF737F89),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(size = 10.dp))
                .clickable { }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Узнать больше",
                    fontSize = 14.sp,
                    color = Color.White,
                    style = semiboldTextStyle
                )

                Spacer(modifier = Modifier.size(5.dp))

                Image(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = null,
                )

            }

        }
    }
}
