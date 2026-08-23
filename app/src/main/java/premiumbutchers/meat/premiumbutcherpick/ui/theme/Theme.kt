package premiumbutchers.meat.premiumbutcherpick.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = ButcherRed,
    onPrimary = Color.White,
    secondary = Sage,
    onSecondary = Color.White,
    background = Cream,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = Color(0xFFF5EAE1),
    onSurfaceVariant = Muted,
    outline = Border
)
private val DarkColors = darkColorScheme(
    primary = ButcherRedLight,
    secondary = Color(0xFF9BC4A7),
    background = Color(0xFF1D1513),
    surface = Color(0xFF281E1B),
    onSurface = Color(0xFFFFEDE5),
    onBackground = Color(0xFFFFEDE5)
)

@Composable
fun ProductAppRUWCZTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
