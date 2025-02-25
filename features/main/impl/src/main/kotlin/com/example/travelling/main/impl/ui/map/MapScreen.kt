package com.example.travelling.main.impl.ui.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.travelling.common.utils.screenWidthDp
import com.example.travelling.main.impl.ui.compose_elements.FiltersButton
import com.example.travelling.main.impl.ui.compose_elements.MyLocationButton
import com.example.travelling.main.impl.ui.map.MapUiState.MapState
import com.yandex.mapkit.mapview.MapView

@Composable
fun MapScreen(
    uiState: MapUiState,
    onMapAction: (MapActions) -> Unit,
    map: MapView
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding() + 52.dp)
        ) {
            AndroidView(
                factory = {
                    map
                },
                modifier = Modifier.fillMaxSize()
            )

            if (uiState.state == MapState.Loading) {
                val width =
                    animateDpAsState(
                        targetValue = if (uiState.state == MapState.Loading) 50.dp else LocalContext.current.screenWidthDp() - 160.dp,
                        label = "width of loading item"
                    )
                val color =
                    animateColorAsState(targetValue = if (uiState.state == MapState.Loading) Color.White else Color.Red)
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 35.dp)
                        .width(width.value)
                        .height(50.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape,
                            clip = true
                        )
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(
                            width = 2.dp,
                            color = color.value,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF74A3FF),
                        strokeWidth = 3.dp,
                        modifier = Modifier.size(28.dp),
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            if (uiState.state is MapState.Content) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 36.dp, end = 8.dp),
                ) {
                    FiltersButton(uiState, onMapAction)
                    Spacer(modifier = Modifier.height(16.dp))
                    MyLocationButton(onMapAction)
                }
            }

            AnimatedVisibility(
                visible = !uiState.isMapOpen,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .clickable { }
                )
            }
        }
    }
}
