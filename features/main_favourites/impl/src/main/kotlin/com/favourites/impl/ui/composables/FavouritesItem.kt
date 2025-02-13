package com.favourites.impl.ui.composables

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.favourites.impl.R
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.boldTextStyle
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.favourites.impl.data.models.local.FavoriteAttraction
import com.favourites.impl.ui.FavouritesAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FavouriteItem(item: FavoriteAttraction, onAction: (FavouritesAction) -> Unit) {

    val selected = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (selected.value) 0.8f else 1f)

    Box(
        modifier = Modifier
            .width(175.dp)
            .height(225.dp)
            .touchAction {
                onAction(FavouritesAction.OnOpenAttraction(item.id))
            }
            .clip(RoundedCornerShape(16.dp))
    ) {
        NetworkImage(
            url = item.icon,
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
                text = item.title,
                style = semiboldTextStyle.copy(fontSize = 11.sp, color = Color.White),
                modifier = Modifier
                    .widthIn(max = 125.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                painter = painterResource(
                    if (item.isLiked) R.drawable.ic_liked else R.drawable.ic_unliked
                ),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(20.dp)
                    .scale(scale)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected.value = false
                                onAction(FavouritesAction.OnLikeChanged(index = item.index))
                            }

                            MotionEvent.ACTION_CANCEL -> {
                                selected.value = false
                            }
                        }
                        true
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {},
                tint = Color.White,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = item.subtitle,
                style = mediumTextStyle.copy(fontSize = 8.sp, color = Color.White),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .widthIn(max = 120.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = item.rating.toString(),
                style = boldTextStyle.copy(fontSize = 14.sp, color = Color(0xFF74A3FF)),
                modifier = Modifier.align(Alignment.BottomEnd),
                maxLines = 1
            )
        }
    }
}
