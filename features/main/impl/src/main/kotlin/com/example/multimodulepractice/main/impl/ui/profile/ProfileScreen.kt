package com.example.multimodulepractice.main.impl.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.ui.compose_elements.HorizontalAttraction

@Composable
fun ProfileScreen(uiState: ProfileUiState, onAction: (ProfileAction) -> Unit) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {
        item {
            ProfileSection(uiState, onAction, context)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )

            Text(
                "Избранное",
                style = semiboldTextStyle.copy(fontSize = 16.sp),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = uiState.recommendedList,
            key = {
                it.id
            }
        ) { item ->

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            HorizontalAttraction(
                context = context,
                attraction = item,
                onProfileAction = onAction
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            )
        }
    }
}