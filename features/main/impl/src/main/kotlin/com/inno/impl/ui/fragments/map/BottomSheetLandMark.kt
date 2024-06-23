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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.data.local.models.Landmark
import com.inno.impl.data.local.models.VideoGidItem

class BottomSheetLandMark(val landmark: Landmark) {
    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun LandmarkBottomSheetPartiallyExpanded() {
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
            Spacer(modifier = Modifier.height(11.dp))

            Text(
                text = "Аудио гид",
                fontSize = 16.sp,
                style = semiboldTextStyle

            )
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun LandmarkBottomSheetFullyExpanded() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            val imageSlider = ImageSlider(
                listOf(
                    "fsdf",
                    "fsdf"
                )
            )
            Box {
                imageSlider.ImageSliderWithTintComposable()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 29.dp, start = 11.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = landmark.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        style = semiboldTextStyle.copy(Color.White)
                    )
                    Text(
                        text = landmark.address,
                        fontSize = 10.sp,
                        style = mediumTextStyle.copy(Color.Gray)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 9.dp)
            ) {
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
                Text(
                    text = "Аудио гид",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = semiboldTextStyle

                )

                Spacer(modifier = Modifier.height(10.dp))

                val videoItem = VideoGidItem(
                    landmark.imageForThumbNail,
                    "Десятиленяя история Иннополиса - как это было",
                    "13:33"
                )
                VideoItemLayout(videoItem = videoItem) {

                }

            }
            Spacer(modifier = Modifier.height(41.dp))

            Row(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
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


    @Composable
    fun VideoItemLayout(
        videoItem: VideoGidItem,
        onClick: () -> Unit
    ) {
        Surface(
            shape = RoundedCornerShape(17.dp),
            shadowElevation = 9.dp,
            modifier = Modifier
                .clickable { onClick() }
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(17.dp)
                    )
                    .padding(9.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Box(
                        Modifier.background(Color.White, RoundedCornerShape(8.dp)),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null,
                            Modifier
                                .size(20.dp)
                                .align(alignment = Alignment.Center),
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(videoItem.photoPreviewUrl)
                                .crossfade(true)
                                .error(R.drawable.ic_dollar_pin)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                    Spacer(modifier = Modifier.width(7.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = videoItem.description,
                            modifier = Modifier.padding(end = 70.dp),
                            fontSize = 12.sp,
                            style = semiboldTextStyle
                        )

                        Spacer(modifier = Modifier.height(31.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(id = R.drawable.timer),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(1.dp))
                            Text(
                                text = videoItem.duration,
                                fontSize = 12.sp,
                                style = semiboldTextStyle.copy(Color.Black)
                            )
                        }
                    }
                }
            }
        }
    }
}