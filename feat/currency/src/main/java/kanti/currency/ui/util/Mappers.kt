package kanti.currency.ui.util

import kanti.curriewer.data.model.currency.CurrencySpan
import kanti.curriewer.ui.components.CurrencyUiState
import kanti.curriewer.ui.components.DynamicUiState

fun CurrencySpan.toCurrencySpan(): kanti.curriewer.ui.components.CurrencySpanUiState {
	return kanti.curriewer.ui.components.CurrencySpanUiState(
		data = CurrencyUiState(
			title = title,
			code = code
		),
		value = value,
		dynamic = DynamicUiState(
			dynamic = dynamic,
			percent = dynamicInPercent
		)
	)
}