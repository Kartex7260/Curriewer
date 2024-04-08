package kanti.currency.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kanti.currency.ui.screens.main.MainScreen

const val CurrencyDestination = "currency_graph"

object CurrencyDestinations {

	const val MAIN = "currency_main"
	const val CURRENCY_DETAIL = "currency_detail"
}

@Composable
fun CurrencyNavHost(
	navController: NavHostController = rememberNavController()
) {
	NavHost(
		navController = navController,
		startDestination = CurrencyDestinations.MAIN
	) {
		composable(route = CurrencyDestinations.MAIN) {
			MainScreen()
		}
		composable(route = CurrencyDestinations.CURRENCY_DETAIL) {
		}
	}
}

@Preview
@Composable
private fun PreviewCurrencyNavHost() {
	CurrencyNavHost()
}