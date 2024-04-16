package kanti.curriewer.domain.currencies

import kanti.curriewer.data.model.currency.CurrencyRepository
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.minusDay
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.ValueIsNullError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import javax.inject.Inject

class GetCurrencySpansUseCase @Inject constructor(
	private val currencyRepository: CurrencyRepository,
	private val getCurrencySpanUseCase: GetCurrencySpanUseCase
) {

	suspend operator fun invoke(
		baseCurrencyCode: String,
		end: Instant = Clock.System.now(),
		start: Instant = end.minusDay(),
		accuracy: RangeAccuracy = RangeAccuracy.DAY
	): DataResult<Flow<CurrencySpan>, DataError> = withContext(Dispatchers.IO) {
		val result = currencyRepository.getAllCurrencyCodes()

		val error = result.error
		if (error != null)
			return@withContext DataResult.Error(error)

		val value = result.value
			?: return@withContext DataResult
				.Error(ValueIsNullError(GetCurrencySpansUseCase::class.java.name))

		val flow = flow {
			value.forEach { currencyCode ->
				launch {
					val spanResult = getCurrencySpanUseCase(
						baseCurrencyCode = baseCurrencyCode,
						currencyCode = currencyCode,
						end = end,
						start = start,
						accuracy = accuracy
					)

					val currencySpan = spanResult.value
					if (currencySpan != null)
						emit(currencySpan)
				}
			}
		}
		return@withContext DataResult.Success(flow)
	}
}