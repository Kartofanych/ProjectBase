package com.inno.impl.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.common.theme.MultimodulePracticeTheme
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.inno.impl.ui.compose_elements.LoadingAnimation
import com.inno.impl.ui.map.MapUiState.MapState
import com.inno.landmark.ui.BottomSheet
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    private val viewModel: MapViewModel by activityViewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                MultimodulePracticeTheme {
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

                            if (uiState.state == MapState.Loading) {
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

                            if (uiState.state == MapState.Error) {
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
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
        MapKitFactory.getInstance().onStop()
    }

}