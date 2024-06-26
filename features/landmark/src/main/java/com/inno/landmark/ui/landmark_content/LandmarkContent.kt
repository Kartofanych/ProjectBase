package com.inno.landmark.ui.landmark_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.common.utils.screenHeightDp
import com.inno.landmark.data.LandmarkResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkContent(landmark: LandmarkResponse, sheetState: SheetState) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(context.screenHeightDp())
            .verticalScroll(scrollState)
    ) {

        ImageSlider(landmark.images, sheetState)

        Spacer(modifier = Modifier.height(10.dp))

        LandmarkInfo(landmark)

    }
}
