package com.search_filters.impl.ui.composables

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.ChipCard
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.search_filters.impl.ui.SearchFiltersAction
import com.search_filters.impl.ui.SearchFiltersUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CitiesSection(uiState: SearchFiltersUiState, onAction: (SearchFiltersAction) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = "Города",
            style = semiboldTextStyle.copy(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(15.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (city in uiState.cities) {
                ChipCard(
                    text = city.name,
                    activeColor = Color(0xFF74A3FF),
                    inActiveColor = Color(0xFFC8D8E9),
                    isActive = city.isActive,
                    modifier = Modifier.height(26.dp),
                    action = {
                        onAction(SearchFiltersAction.CityStateChanged(city.index))
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Скоро их станет больше!",
            style = regularTextStyle.copy(fontSize = 12.sp, color = Color(0xFF959595))
        )
    }
}