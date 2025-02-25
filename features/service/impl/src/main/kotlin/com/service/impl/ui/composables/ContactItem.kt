package com.service.impl.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.ImageType
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.regularTextStyle
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ServiceAction

@Composable
fun ContactItem(contact: Service.Contact, onAction: (ServiceAction) -> Unit) {
    Column(
        modifier = Modifier
            .height(87.dp)
            .width(83.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NetworkImage(
            url = contact.icon,
            type = ImageType.SVG,
            modifier = Modifier
                .size(65.dp)
                .touchAction {
                    onAction(ServiceAction.Deeplink(contact.deeplink))
                }
        )

        Spacer(Modifier.height(7.dp))

        Text(
            text = contact.title,
            style = regularTextStyle.copy(fontSize = 12.sp, color = Color.Black)
        )
    }
}
