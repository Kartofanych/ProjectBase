package com.service.impl.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ServiceAction

@Composable
fun AboutUsBlock(service: Service, onAction: (ServiceAction) -> Unit) {

    var maxLines by remember { mutableIntStateOf(7) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "О нас",
            style = semiboldTextStyle.copy(fontSize = 26.sp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = service.description,
            style = mediumTextStyle.copy(fontSize = 14.sp, color = Color(0xFF535353)),
            maxLines = maxLines,
            modifier = Modifier.touchAction {
                maxLines = if (maxLines == 7) 1000 else 7
            },
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}