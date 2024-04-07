package kanti.curriewer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.Green
import kanti.curriewer.ui.Red

@Composable
fun CurrencyCard(
    modifier: Modifier = Modifier,
    data: CurrencyCardData,
    contentPadding: PaddingValues = PaddingValues(all = 12.dp)
) = Surface(
    modifier = modifier
        .clip(RoundedCornerShape(15.dp)),
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
                text = data.value.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Row {
                DynamicIcon(dynamic = data.dynamic)
                Spacer(modifier = Modifier.width(2.dp))

                val isUp = isUpDynamic(data.dynamic)
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = if (isUp) "+${data.dynamic}%" else "${data.dynamic}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isUpDynamic(data.dynamic)) Green else Red,
                )
            }
        }
    }
}

@Immutable
data class CurrencyCardData(
    val title: String,
    val code: String,
    val value: Float,
    val dynamic: Float
)

@Preview
@Composable
private fun PreviewCurrencyCard() {
    CurriewerTheme {
        Column {
            CurrencyCard(
                data = CurrencyCardData(
                    title = "Russian ruble",
                    code = "RUB",
                    value = 40.54f,
                    dynamic = 1.2f
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            CurrencyCard(
                data = CurrencyCardData(
                    title = "Russian ruble",
                    code = "RUB",
                    value = 40.54f,
                    dynamic = -1.2f
                )
            )
        }
    }
}