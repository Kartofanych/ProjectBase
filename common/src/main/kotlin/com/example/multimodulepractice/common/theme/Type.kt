package com.example.multimodulepractice.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.R

val montserratFamily = FontFamily(
    Font(R.font.regular, FontWeight.Light),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.semibold, FontWeight.SemiBold),
    Font(R.font.bold, FontWeight.Bold),
)

val tabTextStyle = TextStyle(
    fontFamily = montserratFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    letterSpacing = 0.5.sp
)

val mediumTextStyle = TextStyle(
    fontFamily = montserratFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    letterSpacing = 0.5.sp
)


val semiboldTextStyle = TextStyle(
    fontFamily = montserratFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    letterSpacing = 0.5.sp
)

val boldTextStyle = TextStyle(
    fontFamily = montserratFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.5.sp
)