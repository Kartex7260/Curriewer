package kanti.curriewer.data.model.currency

import kanti.curriewer.data.model.currency.datasource.CurrencyData
import kanti.curriewer.data.model.currency.datasource.CurrencyWithTime

fun Map.Entry<String, List<CurrencyWithTime>>.toCurrencySpan(
	titles: List<CurrencyData>
): CurrencySpan {
	val first = value.first()
	val last = value.last()

	val dynamic = last.value - first.value
	val dynamicInPercent = (dynamic / last.value * 100).toFloat()

	return CurrencySpan(
		code = first.code,
		title = titles.firstOrNull { it.code == first.code }?.title ?: first.code,
		value = last.value,
		dynamic = dynamic,
		dynamicInPercent = dynamicInPercent
	)
}