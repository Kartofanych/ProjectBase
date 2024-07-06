package com.inno.impl.ui.map

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import androidx.wear.compose.material.Icon
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.common.utils.screenWidthDp
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.ui.map.MapUiState.MapState
import com.inno.landmark.ui.Landmark
import com.inno.landmark.ui.landmark_content.LandmarkContent
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    uiState: MapUiState,
    onMapAction: (MapActions) -> Unit,
    map: MapView
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                ON_START -> {
                    MapKitFactory.getInstance().onStart()
                    onMapAction(MapActions.OnMapStarted)
                }

                ON_STOP -> {
                    onMapAction(MapActions.OnMapStopped)
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

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    map
                },
                modifier = Modifier.fillMaxSize()
            )

            if (uiState.state == MapState.Error || uiState.state == MapState.Loading) {
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
                        )
                        .clickable(enabled = uiState.state == MapState.Error) {
                            onMapAction(MapActions.OnRelaunchMap)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    when (uiState.state) {
                        MapState.Error -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Что-то с интернетом",
                                    style = semiboldTextStyle.copy(color = Color.Red),
                                    maxLines = 1,
                                    softWrap = false
                                )
                                Text(
                                    text = "Нажмите чтобы перезагрузить",
                                    style = mediumTextStyle.copy(
                                        fontSize = 10.sp, color = Color.Red
                                    ),
                                    maxLines = 1,
                                    softWrap = false
                                )
                            }
                        }

                        else -> {
                            CircularProgressIndicator(
                                color = Color(0xFF47D88D),
                                strokeWidth = 3.dp,
                                modifier = Modifier.size(28.dp),
                                strokeCap = StrokeCap.Round
                            )
                        }
                    }
                }
            }
        }
        if (uiState.currentLandmarkState != Landmark.EMPTY) {
            val sheetState = rememberModalBottomSheetState()
            ModalBottomSheet(
                onDismissRequest = { onMapAction(MapActions.ModalDismissed) },
                sheetState = sheetState,
                dragHandle = null,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            ) {
                when (val state = uiState.currentLandmarkState.state) {
                    is Landmark.LandmarkState.Content -> {
                        LandmarkContent(landmark = state.landmark, sheetState = sheetState) {
                            onMapAction(MapActions.OnOpenGuide)
                        }
                    }

                    Landmark.LandmarkState.Error -> {
                        Box(
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .background(Color.White)
                                .clickable {
                                    onMapAction(MapActions.OnPlaceMarkTapped(uiState.currentLandmarkState.id))
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_reload),
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(60.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Что-то с интернетом",
                                    style = semiboldTextStyle.copy(color = Color.Red),
                                    maxLines = 1,
                                    softWrap = false
                                )
                                Text(
                                    text = "Нажмите чтобы перезагрузить",
                                    style = mediumTextStyle.copy(
                                        fontSize = 10.sp, color = Color.Red
                                    ),
                                    maxLines = 1,
                                    softWrap = false
                                )
                            }
                        }
                    }

                    Landmark.LandmarkState.Loading -> {
                        Box(
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color(0xFF47D88D),
                                strokeWidth = 4.dp,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}