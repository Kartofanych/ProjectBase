package com.attraction.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.travelling.common.data.models.network.ObjectType
import kotlinx.coroutines.flow.Flow

@Composable
fun AttractionEventHandler(
    uiEvent: Flow<AttractionEvent>,
    openGuide: (String) -> Unit,
    back: () -> Unit,
    navigateToAttraction: (String) -> Unit,
    navigateToService: (String) -> Unit,
    navigateToReviews: (String) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is AttractionEvent.OpenGuide -> openGuide(event.attractionId)

                is AttractionEvent.OpenObject -> {
                    when (event.type) {
                        ObjectType.ATTRACTION -> navigateToAttraction(event.id)
                        ObjectType.SERVICE -> navigateToService(event.id)
                    }
                }

                is AttractionEvent.OpenReviews -> navigateToReviews(event.id)

                AttractionEvent.OnBackPressed -> back()
            }
        }
    }
}