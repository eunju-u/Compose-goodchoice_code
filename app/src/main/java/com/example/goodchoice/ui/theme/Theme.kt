package com.example.goodchoice.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object Theme {
    val colorScheme: ThemeColorScheme
        @Composable
        get() = LocalColorScheme.current
}

private val LocalColorScheme = staticCompositionLocalOf {
    LightColorScheme
}

@Composable
fun TestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme: ThemeColorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.white.toArgb()
            WindowCompat.getInsetsController(
                (view.context as Activity).window,
                view
            ).isAppearanceLightStatusBars = !darkTheme
        }
    }

    ProvideColors(colorScheme) {
        MaterialTheme(
            colorScheme = mappingColorScheme(),
            typography = Typography,
            content = content,
            shapes = Shapes
        )
    }
}

@Composable
fun ProvideColors(
    colors: ThemeColorScheme,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalColorScheme provides colorPalette, content = content)
}

@Stable
class ThemeColorScheme(
    blue: Color,
    pureGray: Color,
    red: Color,
    white: Color,
    darkGray: Color,
) {
    var blue by mutableStateOf(blue)
        private set
    var pureGray by mutableStateOf(pureGray)
        private set
    var red by mutableStateOf(red)
        private set
    var white by mutableStateOf(white)
        private set
    var darkGray by mutableStateOf(darkGray)
        private set


    fun copy(): ThemeColorScheme = ThemeColorScheme(
        blue = blue,
        pureGray = pureGray,
        red = red,
        white = white,
        darkGray = darkGray,
    )

    fun update(other: ThemeColorScheme) {
        blue = other.blue
        pureGray = other.pureGray
        red = other.red
        white = other.white
        darkGray = other.darkGray
    }
}

val LightColorScheme = ThemeColorScheme(
    blue = Blue,
    pureGray = PureGray,
    red = OrangeRed,
    white = White,
    darkGray = DarkGray,
)

private val DarkColorScheme = ThemeColorScheme(
    blue = PureBlue,
    pureGray = White,
    red = White,
    white = DarkGray,
    darkGray = White,
)

fun mappingColorScheme() = ColorScheme(
    primary = White,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = White,
    onSecondary = White,
    onTertiary = White,
    secondary = White,
    secondaryContainer = White,
    onSecondaryContainer = White,
    inverseSurface = White,

    tertiary = White,
    tertiaryContainer = White,
    onTertiaryContainer = White,

    inversePrimary = White,

    error = White,
    onError = White,
    errorContainer = White,
    onErrorContainer = White,

    background = White,
    onBackground = White,

    surface = White,
    onSurface = White,
    surfaceTint = White,
    surfaceVariant = White,
    onSurfaceVariant = White,
    inverseOnSurface = White,

    outline = White,
    outlineVariant = White,
    scrim = White,
)
