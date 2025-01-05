package com.search.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.example.multimodulepractice.main_search.impl.R
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.search.impl.ui.ListAction
import com.search.impl.ui.ListUiState

@Composable
fun ListContent(uiState: ListUiState.Content, onAction: (ListAction) -> Unit) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 52.dp)
                .fillMaxSize()
                .background(Color.White)
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
                        .touchAction {
                            onAction(ListAction.OpenSearch)
                        }
                        .background(
                            color = Color(0x1A675151),
                            RoundedCornerShape(18.dp)
                        )
                        .clip(RoundedCornerShape(18.dp))
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
                        text = uiState.hint,
                        style = regularTextStyle.copy(
                            color = Color(0xFF838383),
                            fontSize = 17.sp
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }

            item {
                Text(
                    text = "Именно вам",
                    style = semiboldTextStyle.copy(fontSize = 20.sp),
                    modifier = Modifier.padding(
                        top = 24.dp,
                        start = 20.dp,
                        bottom = 18.dp
                    )
                )

                LazyRow {
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    items(
                        items = uiState.recommendedList,
                        key = {
                            it.id
                        }
                    ) {
                        Row {
                            RecommendedAttractionView(it, context, onAction)
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            items(
                items = uiState.activityGroups,
                key = {
                    it.title
                }
            ) { group ->

                Column {
                    Text(
                        text = group.title,
                        style = semiboldTextStyle.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(
                            horizontal = 20.dp
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    for (activity in group.activities) {
                        ActivityGroupItem(activity, onAction)
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
