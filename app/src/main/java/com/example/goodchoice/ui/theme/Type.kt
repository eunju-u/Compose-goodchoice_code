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
        fontSize = 30.sp,
        lineHeight = 20.sp,
    ),

    displayMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
    ),

    displaySmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 15.sp,
    ),

    //사용하지 않은 type
    bodyLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
    ),

    //사용하지 않은 type
    bodyMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        lineHeight = 15.sp,
    ),

    //사용하지 않은 type
    headlineLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 20.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),

    headlineSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 20.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = GMarketSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 20.sp,
    )
)