package com.inno.impl.ui.fragments.map

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.common.theme.MultimodulePracticeTheme
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.inno.impl.data.local.models.Categories
import com.inno.impl.data.local.models.Landmark
import com.inno.impl.ui.compose_elements.LoadingAnimation
import com.inno.impl.ui.fragments.map.MapUiState.MapState
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
                        val sheetState = rememberModalBottomSheetState()
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

                            if (uiState.currentLandmarkId != null) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        viewModel.onMapAction(MapActions.ModalDismissed)
                                    },
                                    sheetState = sheetState,
                                    modifier = Modifier.heightIn(min = 300.dp),
                                    dragHandle = null,
                                ) {
                                    val lm = Landmark(
                                        listOf(
                                            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
                                            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
                                            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
                                            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg",
                                        ),
                                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
                                        "Университет Иннополис",
                                        "г. Иннополис, ул. Университетская 1",
                                        "Иннополис — это уникальное место в Татарстане," +
                                                " в 40 км от Казани. Целый город, который построили " +
                                                "для айтишников. Здесь работают программисты," +
                                                " разработчики.",
                                        listOf(
                                            Categories("Семейное", Color(0xFF52CE8E)),
                                            Categories("Наука", Color(0xFF74A3FF)),
                                            Categories("Еще что то", Color(0xFFFFC47E)),
                                            Categories("Что то не поместилось", Color(0xFFD795FF))
                                        )
                                    )
                                    val bottomSheetLandMark = BottomSheetLandMark(landmark = lm)
                                    bottomSheetLandMark.LandmarkBottomSheetFullyExpanded()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun PreviewBottomSheet() {
        val lm = Landmark(
            listOf(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg"
            ),
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
            "Университет Иннополис",
            "г. Иннополис, ул. Университетская 1",
            "Иннополис — это уникальное место в Татарстане," +
                    " в 40 км от Казани. Целый город, который построили " +
                    "для айтишников. Здесь работают программисты," +
                    " разработчики.",
            listOf(
                Categories("Семейное", Color(0xFF52CE8E)),
                Categories("Наука", Color(0xFF74A3FF)),
                Categories("Еще что то", Color(0xFFFFC47E)),
                Categories("Что то не поместилось", Color(0xFFD795FF))
            )
        )

        val bottomSheetLandMark = BottomSheetLandMark(landmark = lm)
        bottomSheetLandMark.LandmarkBottomSheetFullyExpanded()
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