package kanti.curriewer.ui.components

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyUiState(
	val title: String = "",
	val code: String = "",
)

@Immutable
data class CurrencySpanUiState(
	val data: CurrencyUiState = CurrencyUiState(title = "", code = ""),
	val value: Double = 0.0,
	val dynamic: DynamicUiState = DynamicUiState(dynamic = 0.0, percent = 0.0f)
)

@Immutable
data class DynamicUiState(
	val dynamic: Double,
	val percent: Float
)