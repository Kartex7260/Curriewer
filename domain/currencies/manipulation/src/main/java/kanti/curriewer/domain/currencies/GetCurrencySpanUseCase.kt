package kanti.curriewer.domain.currencies

import kanti.curriewer.data.model.currency.CurrencyRepository
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.minusDay
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.ValueIsNullError
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
	): DataResult<CurrencySpan, DataError> {
		return withContext(Dispatchers.Default) {
			val result = currencyRepository.getRange(
				baseCurrencyCode = baseCurrencyCode,
				currencyCode = currencyCode,
				end = end,
				start = start,
				accuracy = accuracy
			)

			val error = result.error
			if (error != null)
				return@withContext DataResult.Error(error)

			val range = result.value
				?: return@withContext DataResult
					.Error(ValueIsNullError(GetCurrencySpanUseCase::class.java.name))

			val first = range.first()
			val last = range.last()
			val dynamic = last.value - first.value
			DataResult.Success(
				value = CurrencySpan(
					code = currencyCode,
					title = last.title,
					value = last.value,
					dynamic = dynamic,
					dynamicInPercent = (dynamic / first.value * 100).toFloat()
				)
			)
		}
	}
}