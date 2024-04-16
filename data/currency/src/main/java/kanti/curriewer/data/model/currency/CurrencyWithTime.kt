package kanti.curriewer.data.model.currency

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class CurrencyWithTime(
	val code: String = "",
	val dateTime: Instant = Clock.System.now(),
	val value: Double = 0.0
)