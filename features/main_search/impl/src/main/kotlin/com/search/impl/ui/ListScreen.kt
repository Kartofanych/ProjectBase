package com.search.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main_search.impl.R
import com.search.impl.ui.composables.BigHorizontalAttraction
import com.search.impl.ui.composables.VerticalAttractionView

@Composable
fun ListScreen(uiState: ListUiState, onListAction: (ListAction) -> Unit) {
    val context = LocalContext.current
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
        ) {
            item {
                Text(
                    text = "Находите \nновое с нами",
                    style = semiboldTextStyle.copy(fontSize = 24.sp),
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding() + 30.dp, start = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(color = Color.White, RoundedCornerShape(18.dp))
                        .clip(RoundedCornerShape(18.dp))
                        .clickable {

                        }
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_city),
                        contentDescription = null,
                        tint = Color(0xFF4779D8),
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Text(
                        text = "Иннополис",
                        style = mediumTextStyle.copy(
                            color = Color(0xFF838383),
                            fontSize = 17.sp
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                if (uiState is ListUiState.Content) {
                    Text(
                        text = "Именно вам",
                        style = semiboldTextStyle.copy(fontSize = 20.sp),
                        modifier = Modifier.padding(top = 24.dp, start = 20.dp, bottom = 18.dp)
                    )

                    LazyRow {

                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                        items(
                            items = uiState.popularList,
                            key = {
                                it.id
                            }
                        ) {
                            Row {
                                VerticalAttractionView(it, context, onListAction)
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
                Text(
                    text = "Ближайшие места",
                    style = semiboldTextStyle.copy(fontSize = 20.sp),
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp, bottom = 18.dp)
                )
            }

            if (uiState is ListUiState.Content) {
                items(
                    items = uiState.closeList,
                    key = {
                        it.id
                    }
                ) { item ->
                    BigHorizontalAttraction(
                        context = context,
                        attraction = item,
                        onListAction = onListAction
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                }
            }
        }
    }
}
