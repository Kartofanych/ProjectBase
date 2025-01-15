package com.onboarding.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.onboarding.impl.R
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.onboarding.impl.ui.composables.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(uiState: OnboardingUiState, onAction: (OnboardingAction) -> Unit) {

    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color(0xFF74A3FF),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(R.drawable.squire_background),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.37f, Color(0xFF74A3FF)),
                                Pair(1f, Color(0x0074A3FF)),
                            ),
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .aspectRatio(1.4f)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0f, Color(0x0074A3FF)),
                                Pair(0.63f, Color(0xFF74A3FF)),
                            )
                        )
                    )
            )


            Column(
                modifier = Modifier
                    .padding(
                        bottom = it.calculateBottomPadding() + 96.dp,
                        top = it.calculateTopPadding() + 96.dp
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PageIndicator(
                    numberOfPages = pagerState.pageCount,
                    selectedPage = pagerState.currentPage,
                    defaultRadius = 8.dp,
                    selectedLength = 60.dp,
                    space = 8.dp,
                    animationDurationInMillis = 500,
                )

                HorizontalPager(
                    state = pagerState
                ) { index ->
                    when (index) {
                        0 -> OnboardingPage(
                            title = "Откроем новые\nлокации",
                            subtitle = "Интересные факты из истории, расписание и услуги-все найдется у нас",
                            imageId = R.drawable.onboarding_book
                        )

                        1 -> OnboardingPage(
                            title = "Покажем лучшие\nместа",
                            subtitle = "Все локации тщательно подбираются и обсуждаются с местными жителями",
                            imageId = R.drawable.onboarding_star
                        )

                        else -> OnboardingPage(
                            title = "Подарим хорошие\nскидки",
                            subtitle = "Каждый месяц начисляем вкусные промокоды на самые классные услуги",
                            imageId = R.drawable.onboarding_sale
                        )
                    }
                }

                DefaultButton(
                    onClick = {
                        if (pagerState.currentPage != 2) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onAction(OnboardingAction.OnFinish)
                        }
                    },
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .width(300.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = if (pagerState.currentPage != 2) "Далее" else "Начать путешествие!",
                        style = mediumTextStyle.copy(color = Color.Black, fontSize = 14.sp),
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingPage(
    title: String,
    subtitle: String,
    imageId: Int,
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .heightIn(min = 435.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = title,
            style = semiboldTextStyle.copy(color = Color.White, fontSize = 26.sp),
            textAlign = TextAlign.Center
        )

        Text(
            text = subtitle,
            style = mediumTextStyle.copy(color = Color.White, fontSize = 14.sp),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = Modifier.size(195.dp)
        )
    }
}
