package com.filters.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.filters.impl.ui.composables.CategoriesSection
import com.filters.impl.ui.composables.DistanceSection
import com.filters.impl.ui.composables.HeaderSection
import com.filters.impl.ui.models.FilterModel

@Composable
fun FiltersScreen(uiState: FiltersUiState, onFilterAction: (FiltersAction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderSection()

            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )

            CategoriesSection(mockedItems)

            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )

            DistanceSection(listOf("500m", "1km", "1.5km", "2km", "5km"), 2f) {
                onFilterAction(FiltersAction.Action1)
            }
        }
    }
}


private val mockedItems = mutableListOf(
    FilterModel(text = "Достопримечательности", isSelected = true, 6),
    FilterModel(text = "Развлечения", isSelected = true, 2),
    FilterModel(text = "Инфраструктура", isSelected = true, 1),
    FilterModel(text = "Где поесть", isSelected = false, 10),
    FilterModel(text = "Спорт", isSelected = false, 5),
    FilterModel(text = "Шоппинг", isSelected = false, 3),
    FilterModel(text = "Фотозона", isSelected = false, 1),
    FilterModel(text = "Уборная", isSelected = false, 3),
)
