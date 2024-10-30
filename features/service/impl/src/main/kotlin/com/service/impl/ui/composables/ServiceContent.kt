package com.service.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ServiceAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceContent(service: Service, onAction: (ServiceAction) -> Unit) {

    val scrollState = rememberScrollState()
    val bottomSheetVisibility = remember { mutableStateOf(false) }

    Scaffold {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = it.calculateTopPadding())
        ) {

            ServiceToolbar(
                service = service,
                scrollState = scrollState,
                onAction = onAction
            )

            ServiceBody(service = service, scrollState = scrollState)

            ServiceFooter(
                service = service,
                modifier = Modifier.align(Alignment.BottomCenter),
                openBottomSheet = { bottomSheetVisibility.value = true }
            )
        }
    }
    if (bottomSheetVisibility.value) {
        ModalBottomSheet(
            dragHandle = null,
            onDismissRequest = { bottomSheetVisibility.value = false },
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
                    service.contacts.forEachIndexed { index, contact ->
                        ContactItem(contact, onAction)
                        if (index != service.contacts.size - 1) {
                            Spacer(Modifier.width(22.dp))
                        }
                    }
                }
            }
        }
    }
}
