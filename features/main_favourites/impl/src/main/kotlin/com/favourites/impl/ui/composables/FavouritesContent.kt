package com.favourites.impl.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.favourites.impl.ui.FavoritesToolbar
import com.favourites.impl.ui.FavouritesAction
import com.favourites.impl.ui.FavouritesUiState
import com.favourites.impl.ui.FavouritesUiState.FavouritesState

@Composable
fun FavoritesContent(
    uiState: FavouritesUiState,
    onAction: (FavouritesAction) -> Unit,
) {
    val state = uiState.state
    if (state is FavouritesState.Authorized) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.White
        ) {

            if (state.items.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(it.calculateTopPadding()))
                    }

                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        FavoritesToolbar(uiState, onAction)
                    }

                    items(
                        items = state.items,
                        key = {
                            it.id
                        }
                    ) {
                        FavouriteItem(it, onAction)
                    }

                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        Spacer(modifier = Modifier.height(52.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                ) {
                    FavoritesToolbar(uiState, onAction)

                    Text(
                        text = "Пока что тут\nпусто",
                        style = regularTextStyle.copy(fontSize = 20.sp),
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (uiState.isModalVisible && uiState.state is FavouritesState.Authorized) {
                ProfileModal(uiState.state, onAction, it.calculateBottomPadding())
            }
        }
    }
}
