package com.filters.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.filters.api.data.models.FiltersCategory
import com.filters.impl.ui.FiltersAction

@Composable
fun FilterItem(
    item: FiltersCategory,
    onAction: (FiltersAction) -> Unit
) {
    val (boxColor, textColor) = if (item.isSelected) {
        Color(0xFF74A3FF) to Color.White
    } else {
        Color.White to Color(0xFF959595)
    }
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(color = boxColor, shape = RoundedCornerShape(5.dp))
            .border(1.dp, Color(0xFF74A3FF), shape = RoundedCornerShape(5.dp))
            .clickable {
                onAction(FiltersAction.OnCategoryClicked(item))
            }
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .height(14.dp),
    ) {
        Text(
            text = item.name,
            color = textColor,
            style = semiboldTextStyle.copy(fontSize = 10.sp),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
    }
}