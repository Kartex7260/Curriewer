package kanti.currency.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kanti.currency.ui.screens.home.HomeScreen
import kanti.curriewer.ui.CurriewerTheme
import kanti.curriewer.ui.components.BottomBar

internal object MainDestinations {

	const val HOME = "home"
}

@Composable
fun MainScreen(
	navController: NavHostController = rememberNavController()
) {
	Box {
		NavHost(
			modifier = Modifier
				.align(Alignment.TopCenter)
				.fillMaxWidth(),
			navController = navController,
			startDestination = MainDestinations.HOME
		) {
			composable(
				route = MainDestinations.HOME
			) {
				HomeScreen(
					navController = navController
				)
			}
		}
		BottomBar(
			modifier = Modifier
				.align(Alignment.BottomCenter),
			items = setOf()
		)
	}
}

@Preview
@Composable
private fun PreviewRootHomeScreen() {
	CurriewerTheme {
		Surface {
			MainScreen()
		}
	}
}