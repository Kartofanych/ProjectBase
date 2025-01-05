package com.service.impl.ui.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.example.multimodulepractice.service.impl.R
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.ReviewStarsComponent
import com.example.travelling.common.composables.shimmerBrush
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.service.impl.data.models.local.Service

@Composable
fun ServiceBody(service: Service, scrollState: ScrollState) {
    val pagerState = rememberPagerState(pageCount = { service.images.size })
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 60.dp)
            .verticalScroll(state = scrollState)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
        ) {
            HorizontalPager(state = pagerState) { page ->
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(service.images[page])
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        Box(
                            modifier = Modifier
                                .padding(start = 1.dp, end = 1.dp)
                                .fillMaxSize()
                                .background(
                                    shimmerBrush(
                                        targetValue = 1300f,
                                        showShimmer = true
                                    )
                                )
                        )
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .height(22.dp)
                    .width(60.dp)
                    .background(color = Color(0x99FFFFFF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                    style = semiboldTextStyle.copy(color = Color.Black, fontSize = 13.sp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = service.title,
            style = semiboldTextStyle.copy(
                color = Color.Black,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = service.subtitle,
            style = regularTextStyle.copy(
                color = Color.Black,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ReviewStarsComponent(
                service.ratingBlock.rating.toString(),
                service.ratingBlock.starCount,
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = service.ratingBlock.reviewCount,
                style = regularTextStyle.copy(
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF74A3FF)
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(horizontal = 8.dp)
        ) {
            NetworkImage(
                url = service.organization.icon,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF74A3FF),
                        shape = CircleShape
                    ),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = service.organization.name,
                        style = mediumTextStyle.copy(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = service.organization.rating.toString(),
                        style = semiboldTextStyle.copy(
                            fontSize = 12.sp,
                            color = Color(0xFF737F89)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFF737F89),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = service.description,
            style = regularTextStyle.copy(
                fontSize = 14.sp,
                color = Color(0xFF737F89)
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 20.dp)
        )
    }
}
