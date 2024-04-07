package kanti.curriewer.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.R

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState = remember { HazeState() },
    items: Set<BottomBarItem>
) = Box(
    modifier = modifier
) {
    Row(
        modifier = Modifier
            .height(79.dp)
            .fillMaxWidth()
            .hazeChild(
                state = hazeState,
                style = HazeStyle(
                    tint = Color.Black.copy(alpha = .02f),
                    blurRadius = 20.dp
                )
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (item in items) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(all = 6.dp),
                onClick = { },
                colors = ButtonDefaults.textButtonColors()
            ) {
                Icon(
                    painter = painterResource(id = item.iconId),
                    contentDescription = null
                )
            }
        }
    }
}

@Immutable
data class BottomBarItem(
    @DrawableRes val iconId: Int,
    val route: String
)

@Preview
@Composable
private fun PreviewBottomBar() {
    CurriewerTheme {
        Box {
            val hazeState = remember { HazeState() }
            Image(
                modifier = Modifier
                    .haze(hazeState),
                painter = painterResource(id = R.drawable.cat),
                contentDescription = null
            )
            BottomBar(
                hazeState = hazeState,
                items = setOf(
                    BottomBarItem(
                        iconId = R.drawable.home,
                        route = "home"
                    ),
                    BottomBarItem(
                        iconId = R.drawable.activity,
                        route = "activity"
                    )
                )
            )
        }
    }
}