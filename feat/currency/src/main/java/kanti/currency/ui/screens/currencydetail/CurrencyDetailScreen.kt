package kanti.currency.ui.screens.currencydetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.currency.R
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.components.BoxButton
import kanti.curriewer.ui.components.CurrencyBlock
import kanti.curriewer.ui.components.CurrencyContent
import kanti.curriewer.ui.components.CurrencyData
import kanti.curriewer.ui.components.DynamicData
import kanti.curriewer.ui.components.PlainButton
import kanti.curriewer.ui.components.PlainButtonDefaults

@Composable
fun CurrencyDetailScreen() {
	RootCurrencyDetailScreen(
		currencyData = CurrencyData(
			title = "Test",
			code = "TST",
			value = 1366315.1351,
			dynamic = DynamicData(
				dynamic = 16346.135,
				percent = 3113.515f
			)
		)
	)
}

@Composable
fun RootCurrencyDetailScreen(
	currencyData: CurrencyData,
	currencies: List<CurrencyData> = listOf(),
	onClickCurrencyContent: (code: String) -> Unit = {},
	onBack: () -> Unit = {},
) {
	Column(
		modifier = Modifier.fillMaxSize()
	) {
		// Toolbar
		Spacer(modifier = Modifier.height(8.dp))
		Row(
			modifier = Modifier
				.padding(horizontal = 24.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			BoxButton(onClick = onBack) {
				Icon(
					painter = painterResource(id = R.drawable.back),
					contentDescription = null
				)
			}

			Text(
				text = currencyData.title,
				style = MaterialTheme.typography.titleMedium
			)

			BoxButton(onClick = { }) {
				Icon(
					painter = painterResource(id = R.drawable.chart),
					contentDescription = null
				)
			}
		}
		Spacer(modifier = Modifier.height(8.dp))

		// Content
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
		) {
			item {
				Spacer(modifier = Modifier.height(20.dp))

				// Current currency info
				CurrencyBlock(
					modifier = Modifier.padding(start = 24.dp),
					currencyData = currencyData
				)
				Spacer(modifier = Modifier.height(32.dp))

				// Buttons
				Row(
					modifier = Modifier
						.padding(horizontal = 46.dp)
						.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					PlainButton(
						modifier = Modifier.weight(1f),
						onClick = { }
					) {{ /*TODO*/ }
						Text(text = stringResource(id = R.string.buy))
					}
					Spacer(modifier = Modifier.width(13.dp))
					PlainButton(
						modifier = Modifier.weight(1f),
						colors = PlainButtonDefaults.redColors(),
						onClick = { }
					) {
						Text(text = stringResource(id = R.string.sell))
					}
				}
				Spacer(modifier = Modifier.height(20.dp))

				// Other currencies title
				Row(
					modifier = Modifier
						.padding(start = 24.dp, end = 12.dp)
						.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = stringResource(id = R.string.related_currencies),
						style = MaterialTheme.typography.titleSmall,
						color = MaterialTheme.colorScheme.primaryContainer
					)

					val interactionSource = remember { MutableInteractionSource() }
					val indication = rememberRipple()
					Text(
						modifier = Modifier
							.clip(RoundedCornerShape(5.dp))
							.clickable(
								interactionSource = interactionSource,
								indication = indication,
								role = Role.Button,
								onClick = { }
							)
							.padding(
								vertical = 4.dp,
								horizontal = 12.dp
							),
						text = stringResource(id = R.string.see_all),
						style = MaterialTheme.typography.titleSmall,
						color = MaterialTheme.colorScheme.primaryContainer
					)
				}
			}

			items(
				count = currencies.size,
				key = { index -> currencies[index].code }
			) { otherCurrencyIndex ->
				val otherCurrency = currencies[otherCurrencyIndex]
				CurrencyContent(
					modifier = Modifier
						.clickable(
							interactionSource = remember { MutableInteractionSource() },
							indication = rememberRipple(),
							role = Role.Button,
							onClick = { onClickCurrencyContent(otherCurrency.code) }
						)
						.padding(
							horizontal = 24.dp,
							vertical = 12.dp
						),
					data = otherCurrency
				)
				if (otherCurrencyIndex < currencies.size - 1) {
					HorizontalDivider(
						modifier = Modifier.padding(horizontal = 12.dp)
					)
				}
			}
		}
	}
}

@Preview
@Composable
private fun PreviewRootCurrencyDetailScreen() {
	CurriewerTheme {
		Surface {
			RootCurrencyDetailScreen(
				currencyData = CurrencyData(
					title = "Test",
					code = "TST",
					value = 1366315.1351,
					dynamic = DynamicData(
						dynamic = 16346.135,
						percent = 3113.515f
					)
				),
				currencies = listOf(
					CurrencyData(
						title = "Test",
						code = "TST",
						value = 1366315.1351,
						dynamic = DynamicData(
							dynamic = 16346.135,
							percent = 3113.515f
						)
					),
					CurrencyData(
						title = "Dollar US",
						code = "USD",
						value = .1351,
						dynamic = DynamicData(
							dynamic = -16346.135,
							percent = -3113.515f
						)
					),
					CurrencyData(
						title = "Russian ruble",
						code = "RUB",
						value = 1366315.1351,
						dynamic = DynamicData(
							dynamic = 16346.135,
							percent = 3113.515f
						)
					),
					CurrencyData(
						title = "Test2",
						code = "TS2",
						value = 1366315.1351,
						dynamic = DynamicData(
							dynamic = 16346.135,
							percent = 3113.515f
						)
					)
				)
			)
		}
	}
}