package com.filters.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.models.local.FilterItem
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.filters.impl.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FiltersScreen(uiState: FiltersUiState, onAction: (FiltersAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )
            Categories(uiState.items)

            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )

            Distance()
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .padding(start = 18.dp, top = 20.dp, end = 18.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Сбросить",
            color = Color(0xFF85889E),
            fontSize = 12.sp,
        )
        Text(
            text = "Фильтры",
            color = Color.Black,
            fontSize = 15.sp
        )
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = Color(0xFF000000)
            )
        }
    }
}

@Composable
fun FilterItem(text: String, isSelected: Boolean) {
    val (boxColor, textColor) = if (isSelected) {
        Color(0xFF74A3FF) to Color.White
    } else {
        Color.White to Color(0xFF959595)
    }
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(color = boxColor, shape = RoundedCornerShape(5.dp))
            .border(1.dp, Color(0xFF74A3FF), shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .height(14.dp),
    ) {
        Text(
            text = text,
            color = textColor,
            style = semiboldTextStyle.copy(fontSize = 10.sp),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Distance() {
    Column(
        modifier = Modifier
            .padding(start = 27.dp, end = 12.dp)
    ) {
        Text(
            text = "Дистанция",
            style = semiboldTextStyle.copy(fontSize = 20.sp)
        )
        SliderItem()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Categories(items: List<FilterItem>) {
    Column(
        modifier = Modifier
            .padding(start = 27.dp, end = 12.dp)
    ) {
        Text(
            text = "Категории",
            style = semiboldTextStyle.copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier
            .height(20.dp))

        FlowRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (item in items) {
                com.filters.impl.ui.FilterItem(text = item.text, isSelected = item.isSelected)
            }
        }
    }
}

@Composable
fun SliderItem() {
    var sliderPosition by remember { mutableStateOf(2f) }
    Slider(
        value = sliderPosition,
        valueRange = 0f..4f,
        steps = 3,
        onValueChange = { sliderPosition = it },
        colors = SliderDefaults.colors(
            thumbColor = Color(0xFF74A3FF),
            activeTrackColor = Color(0xFF74A3FF),
            inactiveTrackColor = Color(0xFF74A3FF),
            inactiveTickColor = Color(0xFF3076FF),
            activeTickColor = Color(0xFF3076FF)
        )
    )
}