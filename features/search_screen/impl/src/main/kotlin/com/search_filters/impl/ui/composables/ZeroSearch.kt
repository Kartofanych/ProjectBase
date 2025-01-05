package com.search_filters.impl.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.search_filters.impl.ui.SearchAction
import com.search_filters.impl.ui.SearchUiState.SearchScreenState

@Composable
fun ZeroSearch(zeroState: SearchScreenState.ZeroSearch, onAction: (SearchAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Популярные города",
            style = semiboldTextStyle.copy(
                fontSize = 16.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        for (city in zeroState.popularCities) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAction(SearchAction.ChangeSearchText(city)) }
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = city,
                    style = mediumTextStyle.copy(
                        fontSize = 16.sp,
                        color = Color(0xFF959595)
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
