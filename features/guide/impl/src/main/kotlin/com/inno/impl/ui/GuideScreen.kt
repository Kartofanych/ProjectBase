package com.inno.impl.ui

import android.text.SpannableStringBuilder
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.example.common.composables.shimmerBrush
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.montserratFamily
import com.example.common.utils.getStatusBarHeight
import com.example.common.utils.pxToDp
import com.example.common.utils.screenWidthDp
import com.example.common.utils.toAnnotatedString
import com.example.multimodulepractice.login.R
import kotlinx.coroutines.launch

@Composable
fun GuideScreen(uiState: GuideUiState, onAction: (GuideAction) -> Unit) {
    val scrollState = rememberScrollState()

    val currentScreen = remember { mutableIntStateOf(0) }

    if (uiState is GuideUiState.Content) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ContentImage(uiState.topics[currentScreen.intValue].image)

                Info(
                    HtmlCompat.fromHtml(
                        SpannableStringBuilder(uiState.topics[currentScreen.intValue].text).toString(),
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    ).toAnnotatedString()
                )
            }

            Header(
                modifier = Modifier.safeDrawingPadding(),
                onAction = onAction,
                uiState = uiState,
                currentScreen = currentScreen.intValue,
                scrollState = scrollState
            )

            Footer(
                modifier = Modifier.align(Alignment.BottomCenter),
                uiState = uiState,
                currentScreen = currentScreen,
                scrollState = scrollState
            )
        }
    }

    AnimatedVisibility(visible = uiState == GuideUiState.Loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CircularProgressIndicator(
                color = Color(0xFF47D88D),
                strokeWidth = 4.dp,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun Footer(
    modifier: Modifier,
    uiState: GuideUiState.Content,
    currentScreen: MutableState<Int>,
    scrollState: ScrollState
) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
    ) {

        AnimatedVisibility(
            visible = currentScreen.value > 0,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clip(CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(45.dp)
                    .background(Color(0xFF737F89), CircleShape)
                    .clip(CircleShape)
                    .clickable(currentScreen.value > 0) {
                        currentScreen.value -= 1
                        scope.launch {
                            scrollState.animateScrollTo(0)
                        }
                    },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_play),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 13.dp)
                        .size(18.dp)
                        .rotate(180f)
                )
            }
        }

        AnimatedVisibility(
            visible = currentScreen.value < uiState.topics.size - 1,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0xFF47D88D), CircleShape)
                    .clip(CircleShape)
                    .clickable(currentScreen.value < uiState.topics.size - 1) {
                        currentScreen.value += 1
                        scope.launch {
                            scrollState.animateScrollTo(0)
                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_play),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 13.dp)
                        .size(18.dp)
                )
            }
        }
    }
}

@Composable
fun Header(
    modifier: Modifier,
    onAction: (GuideAction) -> Unit,
    uiState: GuideUiState.Content,
    currentScreen: Int,
    scrollState: ScrollState
) {
    val context = LocalContext.current
    val animatedBackground = animateColorAsState(
        targetValue = when (scrollState.value > 800) {
            true -> Color(0xB2535353)
            else -> Color(0xB2FFFFFF)
        }
    )
    val animatedTint = animateColorAsState(
        targetValue = when (scrollState.value > 800) {
            true -> Color.White
            else -> Color.Black
        }
    )
    Row(
        modifier = modifier
            .padding(top = (context.pxToDp(getStatusBarHeight().toFloat()) + 8).dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(animatedBackground.value, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onAction(GuideAction.OnBackPressed)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = null,
                tint = animatedTint.value
            )
        }

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(animatedBackground.value, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${currentScreen + 1}/${uiState.topics.size}",
                style = mediumTextStyle.copy(fontSize = 14.sp, color = animatedTint.value),
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Composable
fun Info(text: AnnotatedString) {
    val context = LocalContext.current
    val imageHeight = context.screenWidthDp() - 16.dp

    Box(
        modifier = Modifier
            .padding(top = imageHeight)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
    ) {
        Text(
            text = text,
            fontFamily = montserratFamily,
            modifier = Modifier.padding(top = 20.dp)
        )
    }

}

@Composable
fun ContentImage(url: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
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