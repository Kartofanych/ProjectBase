package com.favourites.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.DefaultButton
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.favourites.impl.ui.composables.FavoritesContent
import com.favourites.impl.ui.composables.UnauthorizedContent

@Composable
fun ProfileScreen(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        when (uiState) {
            is FavouritesUiState.Authorized ->
                FavoritesContent(
                    uiState,
                    onAction,
                    it.calculateTopPadding()
                )

            FavouritesUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DefaultButton(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = { onAction(FavouritesAction.OnReload) },
                    ) {
                        Text("reload")
                    }
                }
            }

            FavouritesUiState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())

            FavouritesUiState.Unauthorized -> UnauthorizedContent(uiState, onAction)
        }
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

        NetworkImage(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(45.dp)
                .clip(CircleShape)
                .clickable {
                    onAction(FavouritesAction.OnOpenProfile)
                },
            url = "some",
        )
    }
}