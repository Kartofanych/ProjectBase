package com.example.multimodulepractice.main.impl.ui.profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle

// Mock data
fun mockProfile(): String{
    return "https://upload.wikimedia.org/wikipedia/commons/0/0e/Felis_silvestris_silvestris.jpg"
}


@Composable
fun ProfileSection(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = CornerSize(0.dp),
                    topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(32.dp),
                    bottomStart = CornerSize(32.dp)
                )
            )
            .background(Color.White)
            .padding(16.dp, 8.dp)
            .height(285.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Профиль", style = semiboldTextStyle.copy(fontSize = 18.sp))

            Text(
                "Выйти",
                style = semiboldTextStyle.copy(
                    fontSize = 12.sp,
                    color = Color(0xFF838383)
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { }
                    .padding(12.dp, 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(41.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(16.dp),
                        clip = true
                    )
                    .background(Color.White)
                    .size(108.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(mockProfile())
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Насыбуллин \nКарим",
                    style = semiboldTextStyle.copy(fontSize = 20.sp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "kartofanych@gmail.com",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF838383))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Движение это жизнь",
                    style = regularTextStyle.copy(fontSize = 12.sp)
                )
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        Text(
            "Скоро здесь появится\nстатистика",
            style = regularTextStyle.copy(fontSize = 12.sp, color = Color(0xFF838383)),
            textAlign = TextAlign.Center
        )
    }
}
