package com.inno.landmark.ui.landmark_content


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.landmark.R
import com.inno.landmark.data.LandmarkAudioGid

@Composable
fun AudioGidItemLayout(
    audioGid: LandmarkAudioGid,
    onClick: () -> Unit
) {
    val localContext = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(17.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(17.dp)
            )
            .clickable { onClick() }
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(localContext)
                    .data(audioGid.image.url)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0x1F000000))
                    .clip(RoundedCornerShape(8.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.icon_play),
                contentDescription = null,
                Modifier
                    .size(20.dp)
                    .align(alignment = Alignment.Center),
            )
        }
        Text(
            text = audioGid.title,
            modifier = Modifier
                .padding(start = 98.dp, end = 70.dp),
            style = semiboldTextStyle.copy(fontSize = 12.sp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_timer),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = audioGid.duration,
                fontSize = 12.sp,
                style = semiboldTextStyle.copy(Color.Black)
            )
        }
    }
}
