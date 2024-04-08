package kanti.curriewer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
fun CurrencyBlock(
	modifier: Modifier = Modifier,
	currencyData: CurrencyData,
) = Row(
	modifier = Modifier
		.height(IntrinsicSize.Min)
		.then(modifier)
) {
	val isUpDynamic = currencyData.dynamic.isUpDynamic()
	val plankColor = if (isUpDynamic) Green else Red
	Box(
		modifier = Modifier
			.fillMaxHeight()
			.width(6.dp)
			.background(
				color = plankColor,
				shape = RoundedCornerShape(5.dp)
			)
	)
	Spacer(modifier = Modifier.width(12.dp))
	Column(
		modifier = Modifier
			.fillMaxHeight()
			.padding(vertical = 1.dp),
		verticalArrangement = Arrangement.SpaceBetween,
	) {
		Text(
			text = currencyData.value.formatToString(),
			style = MaterialTheme.typography.headlineLarge
		)
		Spacer(modifier = Modifier.height(4.dp))
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			DynamicIcon(dynamic = currencyData.dynamic.dynamic)
			val dynamic = currencyData.dynamic
			Text(
				text = "${dynamic.dynamic.formatToString()} " +
						"(%${dynamic.percent.removeMinus().formatToString()})",
				style = MaterialTheme.typography.labelSmall
			)
		}
	}
}

@Preview
@Composable
private fun PreviewCurrencyBlock() {
	CurriewerTheme {
		Column {
			CurrencyBlock(
				currencyData = CurrencyData(
					title = "",
					code = "",
					value = 45003.4532,
					dynamic = DynamicData(
						dynamic = 300.332,
						percent = 34.4332f
					)
				)
			)
			Spacer(modifier = Modifier.height(8.dp))
			CurrencyBlock(
				currencyData = CurrencyData(
					title = "",
					code = "",
					value = 45003.4532,
					dynamic = DynamicData(
						dynamic = -300.332,
						percent = -34.4332f
					)
				)
			)
		}
	}
}