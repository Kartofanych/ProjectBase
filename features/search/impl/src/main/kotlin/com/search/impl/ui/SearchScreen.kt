package com.search.impl.ui

import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.search.impl.R
import com.search.impl.ui.SearchUiState.SearchResultsState
import com.search.impl.ui.SearchUiState.SearchScreenState

@Composable
fun SearchScreen(uiState: SearchUiState, onAction: (SearchAction) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {

        SearchField(onAction)

        Box(modifier = Modifier.fillMaxSize()) {
            // android bug https://youtrack.jetbrains.com/issue/KT-48215
            androidx.compose.animation.AnimatedVisibility(
                enter = slideInHorizontally {
                    -it
                },
                exit = slideOutHorizontally {
                    -it
                },
                visible = uiState.state is SearchScreenState.ZeroSearch
            ) {
                if (uiState.state is SearchScreenState.ZeroSearch) {
                    ZeroSearch(zeroState = uiState.state)
                }
            }

            androidx.compose.animation.AnimatedVisibility(
                enter = slideInHorizontally {
                    it
                },
                exit = slideOutHorizontally {
                    it
                },
                visible = uiState.state is SearchScreenState.SearchResults
            ) {
                if (uiState.state is SearchScreenState.SearchResults) {
                    SearchResult(uiState.state, onAction)
                }
            }
        }

    }
}

@Composable
fun SearchActivity(entity: ActivityEntity, onAction: (SearchAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .height(108.dp)
            .clickable {
                onAction(SearchAction.ActivityClicked(entity))
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entity.icon)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(108.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(14.dp)
                    .background(Color.White, RoundedCornerShape(5.dp))
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color(0xFF74A3FF)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = entity.tag,
                    style = mediumTextStyle.copy(
                        fontSize = 10.sp,
                        color = Color(0xFF74A3FF)
                    )
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = entity.title,
                style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.Black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entity.rating.toString(),
                    style = semiboldTextStyle.copy(
                        color = Color(0xFF74A3FF),
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.width(6.dp))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(color = Color(0xFF74A3FF), shape = CircleShape)
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                        .height(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    for (i in 0 until entity.starCount) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Color.White
                        )
                        if (i != entity.starCount - 1) {
                            Spacer(modifier = Modifier.width(2.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = entity.description,
                style = mediumTextStyle.copy(fontSize = 10.sp, color = Color.Black),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = entity.subtitle,
                style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF959595)),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ZeroSearch(zeroState: SearchScreenState.ZeroSearch) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Популярные города",
            style = semiboldTextStyle.copy(
                fontSize = 16.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        for (city in zeroState.popularCities) {

            Text(
                text = city,
                style = mediumTextStyle.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF959595)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SearchResult(searchResults: SearchScreenState.SearchResults, onAction: (SearchAction) -> Unit) {
    when (searchResults.state) {

        SearchResultsState.Loading -> {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }

        is SearchResultsState.Error -> {
            DefaultLoading(modifier = Modifier.fillMaxSize())
        }

        is SearchResultsState.Results -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp)
            ) {
                items(
                    items = searchResults.list,
                    key = {
                        it.id
                    }
                ) {
                    SearchActivity(entity = it, onAction = onAction)
                }
            }
        }
    }
}


@Composable
fun SearchField(onAction: (SearchAction) -> Unit) {
    var value by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(Color.White)
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onAction(SearchAction.ChangeSearchText(it))
                value = it
            },
            modifier = Modifier
                .width(308.dp)
                .height(44.dp)
                .background(Color(0x1A675151), RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(250.dp)
                        .align(Alignment.CenterStart),
                    text = when {
                        value.isBlank() -> "Например, Кремль"
                        else -> value
                    },
                    style = regularTextStyle.copy(
                        color = when {
                            value.isBlank() -> Color(0xFF838383)
                            else -> Color.Black
                        },
                        fontSize = 15.sp
                    ),
                    maxLines = 1
                )

                androidx.compose.animation.AnimatedVisibility(
                    enter = expandIn {
                        IntSize(18, 18)
                    },
                    visible = value.isNotBlank(),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onAction(SearchAction.ChangeSearchText(""))
                                value = ""
                            },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null
                    )
                }
            }
        }
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(30.dp),
            painter = painterResource(id = R.drawable.ic_filters),
            tint = Color(0xFF74A3FF),
            contentDescription = null
        )
    }
}


@Composable
@Preview
fun fu() {
    SearchScreen(
        SearchUiState(
            state = SearchScreenState.SearchResults(
                state = SearchResultsState.Results,
                list = listOf(
                    ActivityEntity(
                        id = "123",
                        icon = "",
                        "Архитектура",
                        type = ActivityEntity.ActivityType.SERVICE,
                        "Университет Иннополис",
                        "г. Иннополис ~ 1 км от вас",
                        "Университет Иннополис занимается образованием, исследованиями и разработками в област...",
                        starCount = 5,
                        4.5f
                    )
                )
            )
        ),
        {}
    )
}