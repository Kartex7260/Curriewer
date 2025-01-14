package kanti.curriewer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.Green
import kanti.curriewer.ui.Red

@Composable
fun CurrencyContent(
	modifier: Modifier = Modifier,
	uiState: CurrencySpanUiState
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			modifier = Modifier.height(50.dp),
			verticalArrangement = Arrangement.SpaceBetween
		) {
			Text(
				modifier = Modifier.padding(top = 3.dp),
				text = uiState.data.title,
				style = MaterialTheme.typography.bodyLarge
			)
			Text(
				modifier = Modifier.padding(bottom = 6.dp),
				text = uiState.data.code,
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
				text = uiState.value.formatToString(),
				style = MaterialTheme.typography.bodyLarge
			)
			Row {
				DynamicIcon(dynamic = uiState.dynamic.dynamic)
				Spacer(modifier = Modifier.width(2.dp))

				val isUp = uiState.dynamic.dynamic.isUpDynamic()
				val dynamicInPercent = uiState.dynamic.percent.formatToString()
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
private fun PreviewCurrencyContent() {
	CurriewerTheme {
		Surface {
			CurrencyContent(
				uiState = CurrencySpanUiState(
					data = CurrencyUiState(
						title = "Test",
						code = "TST"
					),
					value = 1.0,
					dynamic = DynamicUiState(
						dynamic = .5,
						percent = 200.0f
					)
				)
			)
		}
	}
}