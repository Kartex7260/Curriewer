package kanti.curriewer.ui.components

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyData(
	val title: String = "",
	val code: String = "",
	val value: Double = 0.0,
	val dynamic: DynamicData = DynamicData(dynamic = 0.0, percent = 0.0f)
)

@Immutable
data class DynamicData(
	val dynamic: Double,
	val percent: Float
)