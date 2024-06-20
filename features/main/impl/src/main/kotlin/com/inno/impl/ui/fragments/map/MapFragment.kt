package com.inno.impl.ui.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.common.theme.MultimodulePracticeTheme
import com.example.common.theme.mediumTextStyle
import com.example.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
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
                                        .align(Alignment.BottomCenter)
                                        .padding(bottom = 35.dp)
                                        .padding(horizontal = 80.dp)
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .shadow(elevation = 1.dp, shape = CircleShape, clip = false)
                                        .background(Color.White)
                                        .clip(CircleShape)
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
                                    .shadow(elevation = 1.dp, shape = CircleShape, clip = false)
                                    .background(Color.White)
                                    .clip(CircleShape)
                                    .border(
                                        width = 2.dp, color = Color.Red, shape = CircleShape
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
                                    //TODO:put content here
                                    val lm = Landmark(
                                        listOf(
                                        ),
                                        "Университет Иннополис",
                                        "г. Иннополис, ул. Университетская 1",
                                        "Иннополис — это уникальное место в Татарстане," +
                                                " в 40 км от Казани. Целый город, который построили " +
                                                "для айтишников. Здесь работают программисты," +
                                                " разработчики.",
                                        listOf(
                                            Pair("Семейное", Color(0xFF52CE8E)),
                                            Pair("Наука", Color(0xFF74A3FF)),
                                            Pair("Еще что то", Color(0xFFFFC47E)),
                                            Pair("Что то не поместилось", Color(0xFFD795FF))
                                        )
                                    )
                                    LandmarkBottomSheet(landmark = lm)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun LandmarkBottomSheet(landmark: Landmark) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp, 5.dp, 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.drag_handle),
                    contentDescription = "Drag handle"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            val imageSlider = ImageSlider(
                listOf(
                    "fsdf",
                    "fsdf"
                )
            )
            imageSlider.ImageSliderComposable()

            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = landmark.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = semiboldTextStyle.copy()
            )
            Text(
                text = landmark.address,
                fontSize = 10.sp,
                color = Color.Gray,
                style = mediumTextStyle.copy()
            )
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)

            ) {
                for (i in landmark.categories) {
                    Chip(text = i.first, color = i.second)
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Описание",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = semiboldTextStyle.copy()

            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = landmark.description,
                fontSize = 15.sp,
                style = mediumTextStyle.copy()

            )
            Spacer(modifier = Modifier.height(22.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .background(Color(0xFFFFC47E), shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(size = 10.dp))
                        .clickable { }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null
                        )
                        Text(
                            text = "Путеводитель",
                            fontSize = 14.sp,
                            color = Color.White,
                            style = semiboldTextStyle.copy()

                        )
                    }

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .background(Color(0xFF52CE8E), shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(size = 10.dp))
                        .clickable { }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.headphones),
                            contentDescription = null
                        )
                        Text(
                            text = "Аудио гид",
                            fontSize = 14.sp,
                            color = Color.White,
                            style = semiboldTextStyle.copy()

                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Отзывы",
                fontSize = 16.sp,
                style = semiboldTextStyle.copy()

            )
        }
    }

    @Composable
    fun Chip(text: String, color: Color) {
        Box(
            modifier = Modifier
                .background(color, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 12.dp),
        ) {
            Text(
                text = text,
                fontSize = 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = semiboldTextStyle.copy(),
                modifier = Modifier
                    .background(color, shape = RoundedCornerShape(5.dp)),
                maxLines = 1
            )
        }
    }


    @Composable
    @Preview(showBackground = true)
    fun PreviewBottomSheet() {
        val lm = Landmark(
            listOf(),
            "Университет Иннополис",
            "г. Иннополис, ул. Университетская 1",
            "Иннополис — это уникальное место в Татарстане," +
                    " в 40 км от Казани. Целый город, который построили " +
                    "для айтишников. Здесь работают программисты," +
                    " разработчики.",
            listOf(
                Pair("Семейное", Color(0xFF52CE8E)),
                Pair("Наука", Color(0xFF74A3FF)),
                Pair("Еще что то", Color(0xFFFFC47E)),
                Pair("Что то не поместилось", Color(0xFFD795FF))
            )
        )

        LandmarkBottomSheet(landmark = lm)
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