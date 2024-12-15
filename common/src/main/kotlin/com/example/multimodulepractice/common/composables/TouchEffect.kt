package com.example.multimodulepractice.common.composables

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modifier.touchAction(
    onClick: () -> Unit = {}
): Modifier {
    val selected = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (selected.value) 0.9f else 1f)

    return this
        .scale(scale)
        .pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    selected.value = true
                }

                MotionEvent.ACTION_UP -> {
                    selected.value = false
                    onClick()
                }

                MotionEvent.ACTION_CANCEL -> {
                    selected.value = false
                }
            }
            true
        }
}