package com.example.multimodulepractice.main.impl.ui.list

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.main.impl.data.local_models.list.CloseAttraction
import com.example.multimodulepractice.main.impl.data.local_models.list.VerticalAttraction

@Composable
fun ListScreen(uiState: ListUiState, onListAction: (ListAction) -> Unit) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
        ) {
            item {

                Text(
                    text = "Находите \nновое с нами",
                    style = semiboldTextStyle.copy(fontSize = 24.sp),
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding() + 30.dp, start = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(color = Color.White, RoundedCornerShape(18.dp))
                        .clip(RoundedCornerShape(18.dp))
                        .clickable {

                        }
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_city),
                        contentDescription = null,
                        tint = Color(0xFF4779D8),
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Text(
                        text = "Иннополис",
                        style = mediumTextStyle.copy(
                            color = Color(0xFF838383),
                            fontSize = 17.sp
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                if (uiState is ListUiState.Content) {
                    Text(
                        text = "Именно вам",
                        style = semiboldTextStyle.copy(fontSize = 20.sp),
                        modifier = Modifier.padding(top = 24.dp, start = 20.dp, bottom = 18.dp)
                    )

                    LazyRow {

                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                        items(
                            items = uiState.popularList,
                            key = {
                                it.id
                            }
                        ) {
                            Row {
                                VerticalAttractionView(it, context, onListAction)
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
                Text(
                    text = "Ближайшие места",
                    style = semiboldTextStyle.copy(fontSize = 20.sp),
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp, bottom = 18.dp)
                )
            }

            if (uiState is ListUiState.Content) {
                items(
                    items = uiState.closeList,
                    key = {
                        it.id
                    }
                ) {
                    HorizontalAttraction(
                        context = context,
                        attraction = it,
                        onListAction = onListAction
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HorizontalAttraction(
    context: Context,
    attraction: CloseAttraction,
    onListAction: (ListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
            .padding(top = 13.dp)
            .padding(start = 14.dp, end = 8.dp),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .width(208.dp)
            ) {

                Text(
                    text = attraction.name,
                    style = semiboldTextStyle.copy(fontSize = 14.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = attraction.name,
                    style = mediumTextStyle.copy(fontSize = 11.sp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )

                FlowRow(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxLines = 1
                ) {
                    for (category in attraction.categories) {
                        CategoryChip(text = category.name, color = category.color)
                    }
                }

                Text(
                    text = attraction.dateCreation,
                    style = mediumTextStyle.copy(fontSize = 11.sp, color = Color(0xFF959595)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(attraction.icon)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 13.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

    }

}

@Composable
fun CategoryChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .height(14.dp),
    ) {
        Text(
            text = text,
            color = Color.White,
            style = semiboldTextStyle.copy(fontSize = 10.sp),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
    }
}


@Composable
fun VerticalAttractionView(
    attraction: VerticalAttraction,
    context: Context,
    onListAction: (ListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .width(170.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(attraction.icon)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        )
        Text(
            text = attraction.name,
            style = semiboldTextStyle.copy(fontSize = 14.sp),
            modifier = Modifier
                .padding(top = 182.dp)
                .padding(horizontal = 7.dp)
                .fillMaxWidth(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color(0xFF4779D8)
            )

            Text(
                text = attraction.distance,
                style = mediumTextStyle.copy(
                    color = Color(0xFF535353),
                    fontSize = 13.sp
                ),
            )
        }

    }
}