package com.example.travelling.main.impl.ui.compose_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.main.impl.R
import com.example.travelling.main.impl.ui.map.MapActions

@Composable
fun MyLocationButton(onMapAction: (MapActions) -> Unit) {
    FloatingActionButton(
        modifier = Modifier.size(54.dp),
        onClick = {
            onMapAction(MapActions.OnMyLocationClick)
        },
        containerColor = Color.White,
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_my_location),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Black
            )
        }
    }
}
