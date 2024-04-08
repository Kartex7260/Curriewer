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
    Row(
        modifier = Modifier
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.height(50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = data.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = data.code,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = data.value.formatToString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Row {
                DynamicIcon(dynamic = data.dynamic.dynamic)
                Spacer(modifier = Modifier.width(2.dp))

                val isUp = data.dynamic.dynamic.isUpDynamic()
                val dynamicInPercent = data.dynamic.percent.formatToString()
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = if (isUp) "+${dynamicInPercent}%" else "${dynamicInPercent}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isUp) Green else Red,
                )
            }
        }
    }
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