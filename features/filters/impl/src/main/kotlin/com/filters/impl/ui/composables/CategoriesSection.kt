package com.filters.impl.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.semiboldTextStyle
import com.filters.api.data.models.FiltersCategory
import com.filters.impl.ui.FiltersAction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesSection(items: List<FiltersCategory>, onAction: (FiltersAction) -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 27.dp, end = 12.dp)
    ) {
        Text(
            text = "Категории",
            style = semiboldTextStyle.copy(fontSize = 20.sp)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (item in items) {
                FilterItem(item = item, onAction = onAction)
            }
        }
    }
}