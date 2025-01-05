package com.attraction.impl.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.ui.AttractionAction
import com.example.travelling.common.theme.semiboldTextStyle

@Composable
fun ServicesBlock(block: Attraction.CloseObjectsBlock, onAction: (AttractionAction) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            modifier = Modifier.padding(start = 22.dp),
            text = block.title,
            style = semiboldTextStyle.copy(fontSize = 20.sp, color = Color.Black)
        )

        Spacer(Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Spacer(modifier = Modifier.width(22.dp))
            }

            items(
                items = block.closeObjects,
                key = {
                    it.id
                }
            ) {
                ServiceItem(it, onAction)
                Spacer(modifier = Modifier.width(50.dp))
            }
        }
    }
}
