package com.favourites.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.theme.boldTextStyle
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.favourites.impl.R
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun ProfileScreen(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {

    val hazeState = remember { HazeState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyVerticalGrid(
                modifier = Modifier
                    .haze(
                        hazeState,
                        style = HazeStyle(
                            blurRadius = 20.dp,
                            noiseFactor = 0.1f,
                            tint = Color(0xFFFFFFFF)
                        ),
                    )
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Column {
                        Spacer(
                            Modifier.height(it.calculateTopPadding())
                        )
                        FavoritesToolbar(uiState, onAction)
                    }
                }

                items(
                    items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                    key = {
                        it
                    }
                ) {
                    FavoriteItem()
                }

                item {
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .hazeChild(state = hazeState)
                    .height(it.calculateTopPadding())
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun FavoriteItem() {
    Box(
        modifier = Modifier
            .width(175.dp)
            .height(225.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        NetworkImage(
            url = "https://static.tildacdn.com/tild3232-3732-4636-a634-656637356434/c4HYMocHqok.jpg",
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0x80000000),
                            Color(0x00000000),
                            Color(0x80000000),
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Text(
                text = "Университет Иннополис",
                style = semiboldTextStyle.copy(fontSize = 11.sp, color = Color.White),
                modifier = Modifier
                    .widthIn(max = 125.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                painter = painterResource(R.drawable.ic_liked),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(20.dp),
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = "г. Иннополис",
                style = mediumTextStyle.copy(fontSize = 8.sp, color = Color.White),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .widthIn(max = 120.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "4.8",
                style = boldTextStyle.copy(fontSize = 14.sp, color = Color(0xFF74A3FF)),
                modifier = Modifier.align(Alignment.BottomEnd),
                maxLines = 1
            )
        }
    }
}


@Composable
fun FavoritesToolbar(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Избранное",
            style = semiboldTextStyle.copy(fontSize = 24.sp, color = Color.Black),
            modifier = Modifier.align(Alignment.CenterStart)
        )

        NetworkImage(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(45.dp)
                .clip(CircleShape)
                .clickable {

                },
            url = "some",
        )
    }
}