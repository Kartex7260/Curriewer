package kanti.currency.ui.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kanti.currency.R
import kanti.currency.ui.screens.CurrencyDestinations
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.components.CurrencyCard
import kanti.curriewer.ui.components.CurrencyData
import kanti.curriewer.ui.components.DynamicData

@Composable
fun HomeScreen(
	navController: NavController = rememberNavController()
) {
	RootHomeScreen(
		items = listOf(),
		onClickCurrencyCard = { currencyCode ->
			navController.navigate(
				route = "${CurrencyDestinations.CURRENCY_DETAIL}/$currencyCode"
			)
		}
	)
}

@Composable
fun RootHomeScreen(
	items: List<CurrencyData> = listOf(),
	onClickCurrencyCard: (code: String) -> Unit = {},
) {
	LazyColumn(
		modifier = Modifier.padding(horizontal = 16.dp)
	) {
		item {
			Spacer(modifier = Modifier.height(20.dp))
			Text(
				text = stringResource(id = R.string.watchlist),
				style = MaterialTheme.typography.titleMedium
			)
			Spacer(modifier = Modifier.height(12.dp))
		}

		items(
			items = items,
			key = { it.code }
		) { currencyData ->
			CurrencyCard(data = currencyData) {
				onClickCurrencyCard(currencyData.code)
			}
			Spacer(modifier = Modifier.height(16.dp))
		}

		item {
			Spacer(modifier = Modifier.height(79.dp))
		}
	}
}

@Preview
@Composable
private fun PreviewRootHomeScreen() {
	CurriewerTheme {
		Surface {
			RootHomeScreen(
				items = listOf(
					CurrencyData(
						title = "Test",
						code = "TST",
						value = 51514513.351,
						dynamic = DynamicData(
							dynamic = 31624.4672,
							percent = 65.2562f
						)
					),
					CurrencyData(
						title = "Russian ruble",
						code = "RUB",
						value = 13.4531,
						dynamic = DynamicData(
							dynamic = 0.431,
							percent = 1.2441f
						)
					)
				)
			)
		}
	}
}