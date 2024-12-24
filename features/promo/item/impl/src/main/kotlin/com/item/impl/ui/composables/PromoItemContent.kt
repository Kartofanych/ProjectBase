package com.item.impl.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.multimodulepractice.common.composables.DefaultButton
import com.example.multimodulepractice.common.composables.DefaultSeparator
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.promo.item.impl.R
import com.item.impl.data.models.local.PromoItem
import com.item.impl.ui.PromoItemAction

@Composable
fun PromoItemContent(item: PromoItem, onAction: (PromoItemAction) -> Unit) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFF404040)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Start)
                    .size(40.dp)
                    .touchAction {
                        onAction(PromoItemAction.Close)
                    }
                    .padding(8.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.touchAction {
                    onAction(PromoItemAction.OpenInfo(id = item.id, type = item.type))
                }
            ) {
                NetworkImage(
                    url = item.imageUrl,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = item.name,
                    style = semiboldTextStyle.copy(fontSize = 20.sp, color = Color.White)
                )
            }

            DefaultButton(
                onClick = {
                    onAction(PromoItemAction.OpenInfo(id = item.id, type = item.type))
                },
                backgroundColor = Color(0xFF737F89),
                modifier = Modifier
                    .width(300.dp)
                    .height(40.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Показать заведение",
                        style = mediumTextStyle.copy(fontSize = 16.sp, color = Color.White)
                    )

                    Spacer(Modifier.width(6.dp))

                    Icon(
                        painter = painterResource(R.drawable.icon_back),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(180f)
                            .size(20.dp)
                    )
                }
            }

            Text(
                text = item.description,
                style = mediumTextStyle.copy(fontSize = 18.sp, color = Color.White),
                modifier = Modifier.padding(horizontal = 24.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = item.warning,
                style = mediumTextStyle.copy(fontSize = 12.sp, color = Color(0xFF959595)),
                modifier = Modifier.width(192.dp),
                textAlign = TextAlign.Center
            )

            DefaultSeparator(
                modifier = Modifier.padding(horizontal = 44.dp),
                color = Color(0xFF959595)
            )

            Image(
                bitmap = item.code.asImageBitmap(),
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentDescription = null
            )

            DefaultSeparator(
                modifier = Modifier.padding(horizontal = 32.dp),
                color = Color(0xFF959595)
            )

            Text(
                text = item.endDate,
                style = semiboldTextStyle.copy(fontSize = 12.sp, color = Color(0xFF959595))
            )
        }
    }
}
