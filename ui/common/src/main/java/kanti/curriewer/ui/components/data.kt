package kanti.curriewer.ui.components

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyCardData(
	val title: String,
	val code: String,
	val valueDynamic: ValueDynamicData
)

@Immutable
data class ValueDynamicData(
	val value: Float,
	val dynamic: Float,
	val dynamicInPercent: Float
)