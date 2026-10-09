
package com.example.eptracker.ui.theme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AppPrimary,
    onPrimary = AppTextPrimary,
    secondary = AppPrimaryLight,
    onSecondary = AppBackgroundDark,
    tertiary = AppSuccess,
    background = AppBackgroundDark,
    onBackground = AppTextPrimary,
    surface = AppSurfaceDark,
    onSurface = AppTextPrimary,
    surfaceVariant = AppCard,
    onSurfaceVariant = AppTextSecondary,
    outline = AppBorder,
    error = AppError,
    onError = AppBackgroundDark
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = AppTextPrimary,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = AppBackgroundLight,
    onBackground = Color(0xFF191A22),
    surface = AppSurfaceLight,
    onSurface = Color(0xFF191A22)
)

@Composable
fun EpTrackerTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
