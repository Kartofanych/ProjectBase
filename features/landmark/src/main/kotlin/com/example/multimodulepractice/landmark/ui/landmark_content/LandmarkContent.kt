package com.example.multimodulepractice.landmark.ui.landmark_content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.multimodulepractice.landmark.data.Service
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.landmark.R
import com.example.multimodulepractice.landmark.data.LandmarkResponse

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LandmarkContent(
    landmark: LandmarkResponse,
    sheetState: SheetState,
    onOpenGuide: () -> Unit,
    onOpenService: (String) -> Unit
) {

    val columnHeightPx = remember {
        mutableFloatStateOf(1000f)
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onOpenGuide()
                },
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(64.dp),
                containerColor = Color(0xFF74A3FF),
                shape = CircleShape,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_guide),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            )
        },
        content = {
            it
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        columnHeightPx.floatValue = coordinates.size.height.toFloat()
                    }
            ) {

                item {
                    ImageSlider(landmark.images, sheetState, columnHeightPx)

                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
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
                            style = semiboldTextStyle.copy(fontSize = 18.sp)
                        )

                        Spacer(modifier = Modifier.height(9.dp))

                        Text(
                            text = landmark.info,
                            style = regularTextStyle.copy(fontSize = 15.sp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                if (landmark.serviceGroups.isNotEmpty()) {

                    items(
                        items = landmark.serviceGroups,
                        key = {
                            it.title
                        }
                    ) { group ->
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 12.dp)
                                ) {
                                    Text(
                                        text = group.title,
                                        style = semiboldTextStyle.copy(
                                            fontSize = 18.sp,
                                            color = Color(0xFF000000)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = group.subtitle,
                                        style = regularTextStyle.copy(
                                            fontSize = 12.sp,
                                            color = Color(0xFF959595)
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                /*TODO all offers
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Все",
                                        style = mediumTextStyle.copy(fontSize = 14.sp)
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_arrow),
                                        contentDescription = null
                                    )
                                }*/
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            for (item in group.services) {
                                ServiceBlock(item, onOpenService)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    )
}

@Composable
fun ServiceBlock(service: Service, onOpenService: (String) -> Unit) {
    val paddingEnd = remember { mutableStateOf(80.dp) }
    val localDensity = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onOpenService(service.id)
            }
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier.width(68.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(service.icon)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF74A3FF),
                        shape = CircleShape
                    ),
            )

            Row(
                modifier = Modifier
                    .height(16.dp)
                    .background(color = Color(0xFF74A3FF), shape = CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = service.rating.toString(),
                    style = semiboldTextStyle.copy(fontSize = 12.sp, color = Color.White)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 75.dp, end = paddingEnd.value)
        ) {
            Text(
                text = service.title,
                style = semiboldTextStyle.copy(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = service.subtitle,
                style = regularTextStyle.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(color = Color(0xFF74A3FF), shape = CircleShape)
                .clip(CircleShape)
                .onGloballyPositioned { coordinates ->
                    paddingEnd.value = with(localDensity) { (coordinates.size.width + 10).toDp() }
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = service.price,
                style = mediumTextStyle.copy(color = Color.White, fontSize = 14.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
