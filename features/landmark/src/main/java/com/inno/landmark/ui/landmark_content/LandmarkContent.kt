package com.inno.landmark.ui.landmark_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.example.multimodulepractice.landmark.R
import com.inno.landmark.data.LandmarkResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkContent(landmark: LandmarkResponse, sheetState: SheetState, onOpenGuide: () -> Unit) {
    val columnHeightPx = remember {
        mutableFloatStateOf(1000f)
    }
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onOpenGuide()
                },
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(64.dp),
                containerColor = Color(0xFF74A3FF),
                shape = CircleShape,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_guide),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            )
        },
        content = { it ->
            it
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
    )
}
