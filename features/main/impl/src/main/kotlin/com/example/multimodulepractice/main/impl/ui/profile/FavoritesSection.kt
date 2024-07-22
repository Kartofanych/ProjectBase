package com.example.multimodulepractice.main.impl.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.multimodulepractice.landmark.data.AttractionCategory
import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction


fun getMockCloseAttraction(): Attraction {
    return Attraction(
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
fun FavoritesSection(uiState: ProfileUiState) {

    val context = LocalContext.current

}
