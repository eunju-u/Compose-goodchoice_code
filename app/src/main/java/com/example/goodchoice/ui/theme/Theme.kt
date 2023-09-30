package com.example.goodchoice.ui.theme

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
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

//    val configuration = LocalConfiguration.current
//    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

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
    gray: Color,
    yellow: Color,
    pureBlue: Color,
    panoramaBlue: Color,
    green: Color,
    lightPink: Color,
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
    var gray by mutableStateOf(gray)
        private set
    var yellow by mutableStateOf(yellow)
        private set
    var pureBlue by mutableStateOf(pureBlue)
        private set
    var panoramaBlue by mutableStateOf(panoramaBlue)
        private set
    var green by mutableStateOf(green)
        private set
    var lightPink by mutableStateOf(lightPink)
        private set

    fun copy(): ThemeColorScheme = ThemeColorScheme(
        blue = blue,
        pureGray = pureGray,
        red = red,
        white = white,
        darkGray = darkGray,
        gray = gray,
        yellow = yellow,
        pureBlue = pureBlue,
        panoramaBlue = panoramaBlue,
        green = green,
        lightPink = lightPink,
    )

    fun update(other: ThemeColorScheme) {
        blue = other.blue
        pureGray = other.pureGray
        red = other.red
        white = other.white
        darkGray = other.darkGray
        gray = other.gray
        yellow = other.yellow
        pureBlue = other.pureBlue
        panoramaBlue = other.panoramaBlue
        green = other.green
        lightPink = other.lightPink
    }
}

val LightColorScheme = ThemeColorScheme(
    blue = Blue,
    pureGray = PureGray,
    red = OrangeRed,
    white = White,
    darkGray = DarkGray,
    gray = Gray,
    yellow = Yellow,
    pureBlue = PureBlue,
    panoramaBlue = PanoramaBlue,
    green = Green,
    lightPink = LightPink,
)

private val DarkColorScheme = ThemeColorScheme(
    blue = PureBlue,
    pureGray = PureGray,
    red = White,
    white = DarkGray,
    darkGray = White,
    gray = White,
    yellow = Yellow,
    pureBlue = PureBlue,
    panoramaBlue = PanoramaBlue,
    green = Green,
    lightPink = LightPink,
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
