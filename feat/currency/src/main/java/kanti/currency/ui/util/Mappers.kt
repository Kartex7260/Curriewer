package kanti.currency.ui.util

import kanti.curriewer.domain.currencies.CurrencySpan
import kanti.curriewer.ui.components.CurrencyData
import kanti.curriewer.ui.components.DynamicData

fun CurrencySpan.toCurrencyData(): CurrencyData {
	return CurrencyData(
		title = code,
		code = code,
		value = value,
		dynamic = DynamicData(
			dynamic = dynamic,
			percent = dynamicInPercent
		)
	)
}