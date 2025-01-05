package com.reviews.impl.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelling.common.composables.DefaultError
import com.example.travelling.common.composables.DefaultLoading
import com.example.travelling.common.composables.DefaultSeparator
import com.example.travelling.common.composables.ReviewItem
import com.reviews.impl.ui.ReviewsAction
import com.reviews.impl.ui.ReviewsUiState

@Composable
fun ReviewsContent(uiState: ReviewsUiState.Content, onAction: (ReviewsAction) -> Unit) {
    val scrollState = rememberLazyListState()

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }.collect {
            onAction(ReviewsAction.OnScrollAction(it))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { scaffold ->
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                ReviewsToolbar(
                    title = uiState.title,
                    padding = scaffold.calculateTopPadding(),
                    onAction = onAction
                )

                RatingBlock(uiState, onAction)

                Spacer(Modifier.height(30.dp))
            }

            itemsIndexed(
                items = uiState.reviews,
                key = { index, _ -> index }
            ) { _, it ->
                DefaultSeparator()
                Spacer(modifier = Modifier.height(30.dp))
                ReviewItem(it)
            }

            item {
                if (uiState.loading) {
                    DefaultLoading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                if (uiState.error) {
                    DefaultError(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        onReload = { onAction(ReviewsAction.OnReload) }
                    )
                }

                Spacer(Modifier.height(scaffold.calculateBottomPadding() + 20.dp))
            }
        }
    }
}
