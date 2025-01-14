package kanti.curriewer.ui

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private val darkColors = darkColorScheme(
    primary = DarkToolbar1Button,
    onPrimary = DarkOnButton,
    secondary = DarkToolbar2Button,
    onSecondary = DarkOnButton,
    tertiary = DarkSearchButton,
    onTertiary = DarkOnButton,
    primaryContainer = DarkCurrencyCode,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color.White,
    outlineVariant = DarkDivider
)

@Composable
fun CurriewerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColors,
        typography = Typography,
        content = content
    )
}

@Preview
@Composable
private fun PreviewCurriewerTheme() {
    CurriewerTheme {
        Button(onClick = { }) {
            Text(text = "Button")
        }
    }
}