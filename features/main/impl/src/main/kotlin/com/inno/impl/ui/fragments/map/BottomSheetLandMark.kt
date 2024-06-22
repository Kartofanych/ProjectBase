package com.inno.impl.ui.fragments.map

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.data.local.models.Landmark

class BottomSheetLandMark(val landmark: Landmark) {
    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun LandmarkBottomSheet() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp, 5.dp, 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.drag_handle),
                    contentDescription = "Drag handle"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            val imageSlider = ImageSlider(
                listOf(
                    "fsdf",
                    "fsdf"
                )
            )
            imageSlider.ImageSliderComposable()

            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = landmark.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = semiboldTextStyle
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
                    Chip(text = category.name, color = category.categoryColor)
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
                text = landmark.description,
                fontSize = 15.sp,
                style = mediumTextStyle
            )
            Spacer(modifier = Modifier.height(22.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .background(
                            color = Color(0xFFFFC47E),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(size = 10.dp))
                        .clickable { }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null
                        )
                        Text(
                            text = "Путеводитель",
                            fontSize = 14.sp,
                            color = Color.White,
                            style = semiboldTextStyle
                        )
                    }

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .background(
                            color = Color(0xFF52CE8E),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(shape = RoundedCornerShape(size = 10.dp))
                        .clickable { }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.headphones),
                            contentDescription = null
                        )
                        Text(
                            text = "Аудио гид",
                            fontSize = 14.sp,
                            color = Color.White,
                            style = semiboldTextStyle

                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Отзывы",
                fontSize = 16.sp,
                style = semiboldTextStyle

            )
        }
    }

    @Composable
    fun Chip(text: String, color: Color) {
        Box(
            modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(horizontal = 12.dp),
        ) {
            Text(
                text = text,
                fontSize = 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = semiboldTextStyle,
                modifier = Modifier
                    .background(color, shape = RoundedCornerShape(5.dp)),
                maxLines = 1
            )
        }
    }


}