package com.inno.landmark.ui.landmark_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.inno.landmark.data.LandmarkResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkContent(landmark: LandmarkResponse, sheetState: SheetState, onOpenGuide: () -> Unit) {
    val columnHeightPx = remember {
        mutableFloatStateOf(1000f)
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                columnHeightPx.floatValue = coordinates.size.height.toFloat()
            }
            .verticalScroll(scrollState)
    ) {

        ImageSlider(landmark.images, sheetState, columnHeightPx)

        Spacer(modifier = Modifier.height(10.dp))

        LandmarkInfo(landmark, onOpenGuide)

    }
}
