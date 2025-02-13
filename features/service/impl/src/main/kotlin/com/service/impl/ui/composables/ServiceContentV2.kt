package com.service.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.composables.DefaultSeparator
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ReviewModalState
import com.service.impl.ui.ServiceAction
import com.service.impl.ui.ServiceUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceContentV2(
    uiState: ServiceUiState,
    reviewModalState: ReviewModalState,
    onAction: (ServiceAction) -> Unit
) {
    val service = (uiState.state as? ServiceUiState.DataState.Content)?.service ?: return

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { scaffold ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(bottom = scaffold.calculateBottomPadding()),
            ) {

                ImageSlider(scaffold.calculateTopPadding(), service.images, onAction)

                Spacer(modifier = Modifier.height(16.dp))

                InformationBlock(
                    uiState = uiState,
                    onAction = onAction,
                )

                DefaultSeparator()

                Spacer(modifier = Modifier.height(24.dp))

                AboutUsBlock(
                    service = service,
                    onAction = onAction,
                )

                DefaultSeparator()

                Spacer(modifier = Modifier.height(24.dp))

                if (service.serviceTypes.isNotEmpty()) {
                    ServicesBlock(
                        service = service,
                        onAction = onAction
                    )

                    DefaultSeparator()

                    Spacer(modifier = Modifier.height(24.dp))
                }

                ReviewBlock(uiState, onAction)
            }

            AnimatedVisibility(
                visible = uiState.isScheduleVisible,
                enter = slideInHorizontally { it },
                exit = slideOutHorizontally { it },
            ) {
                ScheduleScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(top = scaffold.calculateTopPadding() + 10.dp),
                    onAction = onAction,
                    schedule = service.schedule
                )
            }

            ReviewModal(reviewModalState, onAction, scaffold.calculateBottomPadding())

            if (uiState.isCallBottomSheetVisible) {
                ModalBottomSheet(
                    dragHandle = null,
                    onDismissRequest = { onAction(ServiceAction.ChangeCallBottomSheetVisibility) },
                ) {

                    Column(
                        modifier = Modifier
                            .height(174.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Связаться с нами",
                            style = mediumTextStyle.copy(fontSize = 16.sp, color = Color.Black)
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                        ) {
                            service.infoBlock.contacts.forEachIndexed { index, contact ->
                                ContactItem(contact, onAction)
                                if (index != service.infoBlock.contacts.size - 1) {
                                    Spacer(Modifier.width(22.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ServicesBlock(service: Service, onAction: (ServiceAction) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "Наши услуги",
            style = semiboldTextStyle.copy(fontSize = 26.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        service.serviceTypes.forEach { serviceType ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DefaultButton(
                    modifier = Modifier
                        .height(22.dp)
                        .width(80.dp),
                    backgroundColor = Color(0xFF74A3FF),
                    onClick = {
                        if (service.infoBlock.contacts.isNotEmpty()) {
                            onAction(ServiceAction.ChangeCallBottomSheetVisibility)
                        }
                    },
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(
                        text = serviceType.price,
                        style = mediumTextStyle.copy(fontSize = 12.sp, color = Color.White)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = serviceType.title,
                    style = mediumTextStyle.copy(fontSize = 14.sp, color = Color(0xFF535353)),
                    maxLines = 2,
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        Spacer(modifier = Modifier.height(14.dp))
    }
}
