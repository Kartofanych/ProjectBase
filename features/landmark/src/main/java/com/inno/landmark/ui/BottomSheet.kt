package com.inno.landmark.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.inno.landmark.ui.landmark_content.LandmarkContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit, state: LandMarkState) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        when (state) {
            is LandMarkState.Content -> LandmarkContent(
                landmark = state.landmark,
                sheetState = sheetState
            )

            LandMarkState.Error -> {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .background(Color.White)
                )
            }

            LandMarkState.Loading -> {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .background(Color.Green)
                )
            }
        }
    }
}