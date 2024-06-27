package com.inno.impl.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.inno.impl.ui.compose_elements.LoadingAnimation
import com.inno.landmark.ui.BottomSheet
import com.yandex.mapkit.MapKitFactory

@Composable
fun MapScreen(viewModel: MapViewModel) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                ON_START -> {
                    MapKitFactory.getInstance().onStart()
                    viewModel.onStart()
                }

                ON_STOP -> {
                    viewModel.onStop()
                    MapKitFactory.getInstance().onStop()
                }

                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val uiState = viewModel.uiStateFlow.collectAsState().value

        MapEventHandler(uiEvent = viewModel.uiEvent)

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    viewModel.map
                }, modifier = Modifier.fillMaxSize()
            )

            if (uiState.state == MapUiState.MapState.Loading) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 35.dp)
                        .padding(horizontal = 80.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .background(color = Color.White)
                        .clip(shape = CircleShape)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = "Загрузка мест", style = semiboldTextStyle
                        )
                        LoadingAnimation(
                            modifier = Modifier
                                .align(Alignment.Bottom)
                                .padding(bottom = 4.dp, start = 1.dp)
                        )
                    }
                }
            }

            if (uiState.state == MapUiState.MapState.Error) {
                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
                    .padding(horizontal = 80.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(
                        elevation = 1.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(Color.White)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .clickable {
                        viewModel.launch()
                    }) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Что-то с интернетом",
                            style = semiboldTextStyle.copy(color = Color.Red)
                        )
                        Text(
                            text = "Нажмите чтобы перезагрузить",
                            style = mediumTextStyle.copy(
                                fontSize = 10.sp, color = Color.Red
                            )
                        )
                    }
                }
            }

            if (uiState.currentLandmarkState != null) {
                BottomSheet(
                    { viewModel.onMapAction(MapActions.ModalDismissed) },
                    uiState.currentLandmarkState
                )
            }
        }
    }
}