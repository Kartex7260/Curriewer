package kanti.curriewer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.Green
import kanti.curriewer.ui.R
import kanti.curriewer.ui.Red

@Composable
fun DynamicIcon(
    modifier: Modifier = Modifier,
    dynamic: Double
) {
    val isUp = dynamic.isUpDynamic()
    val iconId = if (isUp) R.drawable.arrow_up else R.drawable.arrow_bottom
    val color = if (isUp) Green else Red
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(size = 16.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color)
        )
    }
}

@Preview
@Composable
private fun PreviewDynamicIcon() {
    CurriewerTheme {
        Column {
            DynamicIcon(dynamic = 1.0)
            DynamicIcon(dynamic = -1.0)
        }
    }
}