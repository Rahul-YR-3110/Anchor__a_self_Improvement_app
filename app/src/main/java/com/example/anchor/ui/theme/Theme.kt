package com.example.anchor.ui.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// 1. Define your custom dark color scheme mapping here
private val CustomDarkColorScheme = darkColorScheme(
    background = BackgroundDark,
    surface = CardDark,                  // Standard small cards
    surfaceVariant = MainCardLight,      // The special light water card
    primary = TerracottaAccent,          // The orange "+" button
    secondary = LightTerracotta,
    onBackground = TextWhite,            // Text on main background
    onSurface = TextWhite,               // Text on standard cards
    onSurfaceVariant = TextDarkBrown,    // Dark text inside the light card
)

// Optional: Fallback light scheme if you ever turn off dark mode
private val CustomLightColorScheme = lightColorScheme(
    primary = TerracottaAccent,
    background = TextWhite,
    surface = MainCardLight
)

@Composable
fun AnchorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        // 3. Point these to your new custom configurations
        darkTheme -> CustomDarkColorScheme
        else -> CustomLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}