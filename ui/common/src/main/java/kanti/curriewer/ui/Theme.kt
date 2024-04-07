package kanti.curriewer.ui

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

private val lightColors = lightColorScheme(
    surface = LightSurface,
    surfaceVariant = LightSurfaceVariant
)

private val darkColors = darkColorScheme(
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant
)

@Composable
fun CurriewerTheme(
    darkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkMode) darkColors else lightColors
    MaterialTheme(
        colorScheme = colorScheme,
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