package com.favourites.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.favourites.impl.R
import com.example.travelling.common.composables.DefaultSeparator
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.semiboldTextStyle
import com.favourites.impl.ui.FavouritesAction
import com.favourites.impl.ui.FavouritesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileModal(
    uiState: FavouritesUiState.FavouritesState.Authorized,
    onAction: (FavouritesAction) -> Unit,
    bottomPadding: Dp,
) {
    ModalBottomSheet(
        dragHandle = null,
        onDismissRequest = {
            onAction(FavouritesAction.ChangeProfileModalVisibility(false))
        },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = bottomPadding),
        ) {
            Spacer(Modifier.height(18.dp))

            Text(
                text = "Профиль",
                style = semiboldTextStyle.copy(fontSize = 24.sp, color = Color.Black)
            )

            Spacer(Modifier.height(28.dp))

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                NetworkImage(
                    url = uiState.user.image,
                    modifier = Modifier
                        .size(96.dp)
                        .touchAction()
                        .border(width = 3.dp, color = Color(0xFF74A3FF), shape = CircleShape)
                        .clip(CircleShape)
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    text = uiState.user.name,
                    style = semiboldTextStyle.copy(fontSize = 16.sp, color = Color.Black)
                )
            }

            Spacer(Modifier.height(34.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF2F3F5),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 18.dp)
            ) {

                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .touchAction {
                            onAction(FavouritesAction.OpenPromo)
                        }
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_promo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF2A2A2A)
                    )

                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = "Промо",
                        style = semiboldTextStyle.copy(
                            fontSize = 14.sp,
                            color = Color(0xFF2A2A2A)
                        ),
                        modifier = Modifier.width(230.dp)
                    )

                    Box(
                        Modifier
                            .height(20.dp)
                            .widthIn(min = 20.dp)
                            .background(color = Color(0xFF74A3FF), shape = CircleShape)
                    ) {
                        Text(
                            text = uiState.user.promoCount.toString(),
                            style = semiboldTextStyle.copy(
                                fontSize = 12.sp,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 4.dp)
                        )
                    }
                }

                DefaultSeparator()

                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .touchAction {
                            onAction(FavouritesAction.ChangeProfileModalVisibility(false))
                            onAction(FavouritesAction.OnLogOut)
                        }
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_exit),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFFFD5539)
                    )

                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = "Выйти из аккаунта",
                        style = semiboldTextStyle.copy(
                            fontSize = 14.sp,
                            color = Color(0xFFFD5539)
                        )
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}