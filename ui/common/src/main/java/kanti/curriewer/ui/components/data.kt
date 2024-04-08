package kanti.curriewer.ui.components

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyData(
	val title: String,
	val code: String,
	val value: Double,
	val dynamic: DynamicData
)

@Immutable
data class DynamicData(
	val dynamic: Double,
	val percent: Float
)