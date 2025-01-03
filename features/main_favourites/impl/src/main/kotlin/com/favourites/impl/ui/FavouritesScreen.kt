package com.favourites.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.DefaultError
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.favourites.impl.ui.FavouritesUiState.FavouritesState
import com.favourites.impl.ui.composables.FavoritesContent
import com.favourites.impl.ui.composables.UnauthorizedContent

@Composable
fun ProfileScreen(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {

    when (uiState.state) {
        is FavouritesState.Authorized ->
            FavoritesContent(
                uiState,
                onAction,
            )

        FavouritesState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = {
                onAction(FavouritesAction.OnReload)
            }
        )

        FavouritesState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())

        FavouritesState.Unauthorized -> UnauthorizedContent(uiState, onAction)
    }
}

@Composable
fun FavoritesToolbar(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Избранное",
            style = semiboldTextStyle.copy(fontSize = 24.sp, color = Color.Black),
            modifier = Modifier.align(Alignment.CenterStart)
        )

        AnimatedVisibility(
            visible = uiState.state is FavouritesState.Authorized,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            if (uiState.state is FavouritesState.Authorized) {
                Box(
                    modifier = Modifier
                        .height(47.dp)
                        .wrapContentWidth()
                        .touchAction {
                            onAction(FavouritesAction.ChangeProfileModalVisibility(true))
                        }
                ) {
                    NetworkImage(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .size(45.dp)
                            .clip(CircleShape),
                        url = uiState.state.user.image,
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .height(17.dp)
                            .widthIn(min = 17.dp)
                            .background(color = Color(0xFF74A3FF), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.state.user.promoCount.toString(),
                            style = mediumTextStyle.copy(color = Color.White, fontSize = 11.sp),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
