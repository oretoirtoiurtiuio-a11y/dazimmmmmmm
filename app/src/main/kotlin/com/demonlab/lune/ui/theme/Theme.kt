package com.demonlab.lune.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DazaiBlue,
    secondary = DazaiBlueLight,
    tertiary = DazaiBlueDeep,
    background = DazaiBlack,
    surface = DazaiDark,
    onPrimary = DazaiWhite,
    onSecondary = DazaiWhite,
    onTertiary = DazaiWhite,
    onBackground = DazaiWhite,
    onSurface = DazaiGrayLight,
    surfaceVariant = DazaiSurface,
    onSurfaceVariant = DazaiGrayMuted
)

@Composable
fun DazaiPlayerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    amoledMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> if (amoledMode) DarkColorScheme.copy(background = AMOLEDBlack, surface = AMOLEDBlack) else DarkColorScheme
        else -> lightColorScheme() // Simplified for now
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
