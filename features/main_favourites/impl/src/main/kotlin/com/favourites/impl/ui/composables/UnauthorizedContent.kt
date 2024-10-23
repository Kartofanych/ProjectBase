package com.favourites.impl.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.DefaultButton
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.favourites.impl.ui.FavoritesToolbar
import com.favourites.impl.ui.FavouritesAction
import com.favourites.impl.ui.FavouritesUiState

@Composable
fun UnauthorizedContent(uiState: FavouritesUiState, onAction: (FavouritesAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        FavoritesToolbar(uiState, onAction)

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Зарегистрируйтесь чтобы сохранять любимые места",
                style = regularTextStyle.copy(color = Color(0xFF535353), fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            DefaultButton(
                onClick = {
                    onAction(FavouritesAction.OnLogIn)
                },
                content = {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        text = "Войти",
                        style = semiboldTextStyle.copy(color = Color.White, fontSize = 14.sp)
                    )
                }
            )
        }
    }
}
