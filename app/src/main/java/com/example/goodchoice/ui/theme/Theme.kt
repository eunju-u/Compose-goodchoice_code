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
    mediumPurple: Color,
    darkPurple: Color,
    pureGray1: Color,
    dim: Color,
    blue1: Color,
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
    var mediumPurple by mutableStateOf(mediumPurple)
        private set
    var darkPurple by mutableStateOf(darkPurple)
        private set
    var pureGray1 by mutableStateOf(pureGray1)
        private set
    var dim by mutableStateOf(dim)
        private set
    var blue1 by mutableStateOf(blue1)
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
        mediumPurple = mediumPurple,
        darkPurple = darkPurple,
        pureGray1 = pureGray1,
        dim = dim,
        blue1 = blue1,
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
        mediumPurple = other.mediumPurple
        darkPurple = other.darkPurple
        pureGray1 = pureGray1
        dim = dim
        blue1 = blue1
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
    mediumPurple = MediumPurple,
    darkPurple = DarkPurple,
    pureGray1 = PureGray,
    dim = DarkGray,
    blue1 = Blue,
)

private val DarkColorScheme = ThemeColorScheme(
    blue = PureBlue,
    pureGray = DarkGray,
    red = White,
    white = MediumGray,
    darkGray = White,
    gray = PureGray,
    yellow = Yellow,
    pureBlue = PureBlue,
    panoramaBlue = PanoramaBlue,
    green = Green,
    lightPink = LightPink,
    mediumPurple = MediumPurple,
    darkPurple = DarkPurple,
    pureGray1 = PureGray,
    dim = DarkGray,
    blue1 = Blue,
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
