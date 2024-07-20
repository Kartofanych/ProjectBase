package com.example.multimodulepractice.main.impl.ui.profile

import android.content.Context
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
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.landmark.data.AttractionCategory
import com.example.multimodulepractice.main.impl.data.local_models.list.CloseAttraction
import com.example.multimodulepractice.main.impl.ui.compose_elements.HorizontalAttraction


fun getMockCloseAttraction(): CloseAttraction {
    return CloseAttraction(
        id = "1",
        name = "Example Attraction",
        distance = "5 km",
        icon = "https://upload.wikimedia.org/wikipedia/commons/0/0e/Felis_silvestris_silvestris.jpg",
        shortInfo = "This is a short description of the attraction.",
        categories = listOf(
            AttractionCategory(name = "Category 1", color = Color(0xFFE57373)),
            AttractionCategory(name = "Category 2", color = Color(0xFF81C784))
        ),
        dateCreation = "2024-07-20"
    )
}

@Composable
fun FavoritesSection(context: Context) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Избранное",
            style = semiboldTextStyle.copy(fontSize = 16.sp),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        HorizontalAttraction(
            context = context,
            attraction = getMockCloseAttraction(),
            onListAction = {}
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        HorizontalAttraction(
            context = context,
            attraction = getMockCloseAttraction(),
            onListAction = {}
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        HorizontalAttraction(
            context = context,
            attraction = getMockCloseAttraction(),
            onListAction = {}
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}
