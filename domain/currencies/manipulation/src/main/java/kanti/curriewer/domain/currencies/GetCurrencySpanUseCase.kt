package kanti.curriewer.domain.currencies

import kanti.curriewer.data.model.currency.CurrencyRepository
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.minusDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class GetCurrencySpanUseCase @Inject constructor(
	private val currencyRepository: CurrencyRepository
) {

	suspend operator fun invoke(
		baseCurrencyCode: String,
		currencyCode: String,
		end: Instant = Clock.System.now(),
		start: Instant = end.minusDay(),
		accuracy: RangeAccuracy = RangeAccuracy.DEFAULT
	): CurrencySpan {
		return withContext(Dispatchers.Default) {
			val range = currencyRepository.getRange(
				baseCurrencyCode = baseCurrencyCode,
				currencyCode = currencyCode,
				end = end,
				start = start,
				accuracy = accuracy
			)
			val first = range.first()
			val last = range.last()
			val dynamic = last.value - first.value
			CurrencySpan(
				code = currencyCode,
				value = last.value,
				dynamic = dynamic,
				dynamicInPercent = (dynamic / first.value * 100).toFloat()
			)
		}
	}
}