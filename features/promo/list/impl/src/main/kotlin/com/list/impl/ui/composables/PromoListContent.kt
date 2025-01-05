package com.list.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.promo.list.impl.R
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.semiboldTextStyle
import com.list.impl.ui.PromoListAction
import com.list.impl.ui.PromoListUiState

@Composable
fun PromoListContent(uiState: PromoListUiState.Content, onAction: (PromoListAction) -> Unit) {

    Scaffold(
        modifier = Modifier.background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_arrow),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .rotate(180f)
                            .size(32.dp)
                            .touchAction {
                                onAction(PromoListAction.Close)
                            },
                        contentDescription = null,
                        tint = Color.Black
                    )

                    Text(
                        text = "Ваши промокоды",
                        style = semiboldTextStyle.copy(fontSize = 20.sp, color = Color.Black),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(Modifier.height(16.dp))
            }

            items(
                items = uiState.list,
                key = { item -> item.token }
            ) { item ->
                PromoItem(item, onAction)

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
