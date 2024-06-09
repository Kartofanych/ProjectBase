package com.inno.impl.ui.fragments.map_fragment

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.common.di.AppContext
import com.example.common.utils.injectedViewModel
import com.inno.impl.di.DaggerMapComponent
import com.inno.impl.di.MainScope
import com.inno.impl.di.MapDependencies
import com.yandex.mapkit.mapview.MapView
import javax.inject.Inject

@MainScope
class MapFragmentViewHolder @Inject constructor(
    @AppContext
    private val context: Context,
    private val dependencies: MapDependencies
) {

    private val mapView = MapView(context)

    private val daggerMapComponent by lazy {
        DaggerMapComponent.factory().create(dependencies, mapView)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MapFragment() {
        val viewModel = injectedViewModel { daggerMapComponent.mapViewModel() }
        val sheetState = rememberModalBottomSheetState()
        val uiState = viewModel.uiStateFlow.collectAsState().value

        MapEventHandler(uiEvent = viewModel.uiEvent)

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        viewModel.onStart()
                    }

                    Lifecycle.Event.ON_STOP -> {
                        viewModel.onStop()
                    }

                    else -> Unit
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    mapView
                },
                modifier = Modifier.fillMaxSize()
            )

            if (uiState.currentLandmarkId != null) {
                ModalBottomSheet(
                    onDismissRequest = {
                        viewModel.onMapAction(MapActions.ModalDismissed)
                    },
                    sheetState = sheetState,
                    modifier = Modifier.heightIn(min = 300.dp)
                ) {}
            }
        }
    }
}
