package kanti.curriewer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.Green
import kanti.curriewer.ui.Red

@Composable
fun CurrencyCard(
    modifier: Modifier = Modifier,
    data: CurrencyData,
    contentPadding: PaddingValues = PaddingValues(all = 12.dp),
    onClick: () -> Unit = {}
) = Surface(
    modifier = modifier
        .clip(RoundedCornerShape(15.dp))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
            role = Role.Button,
            onClick = onClick
        ),
    color = MaterialTheme.colorScheme.surfaceVariant
) {
    CurrencyContent(
        modifier = Modifier
            .padding(contentPadding),
        data = data
    )
}

@Preview
@Composable
private fun PreviewCurrencyCard() {
    CurriewerTheme {
        Column {
            CurrencyCard(
                data = CurrencyData(
                    title = "Russian ruble",
                    code = "RUB",
                    value = 11540.111,
                    dynamic = DynamicData(
                        dynamic = 1111.111,
                        percent = 1111.111f
                    )
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            CurrencyCard(
                data = CurrencyData(
                    title = "Russian ruble",
                    code = "RUB",
                    value = 11540.111,
                    dynamic = DynamicData(
                        dynamic = -1111.111,
                        percent = -1111.111f
                    )
                )
            )
        }
    }
}