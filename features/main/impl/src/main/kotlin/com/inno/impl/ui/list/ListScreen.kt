package com.inno.impl.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R

@Composable
fun ListScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
        ) {

            Text(
                text = "Находите \nновое с нами",
                style = semiboldTextStyle.copy(fontSize = 24.sp),
                modifier = Modifier
                    .safeDrawingPadding()
                    .padding(top = it.calculateTopPadding() + 30.dp, start = 20.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(color = Color.White, RoundedCornerShape(18.dp))
                    .clip(RoundedCornerShape(18.dp))
                    .clickable {

                    }
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_city),
                    contentDescription = null,
                    tint = Color(0xFF4779D8),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp)
                )

                Text(
                    text = "Иннополис",
                    style = mediumTextStyle.copy(
                        color = Color(0xFF838383),
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}