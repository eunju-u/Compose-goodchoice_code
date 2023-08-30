package com.example.goodchoice.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.goodchoice.R

val GMarketSansFamily = FontFamily(
    Font(R.font.g_market_sans_light, FontWeight.Normal),
    Font(R.font.g_market_sans_medium, FontWeight.Medium),
    Font(R.font.g_market_sans_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    displayMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    displaySmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    headlineLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    headlineMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    headlineSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    titleLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    titleMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    titleSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    bodyLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    bodyMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    bodySmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    labelLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    labelMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    ),

    labelSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    )
)