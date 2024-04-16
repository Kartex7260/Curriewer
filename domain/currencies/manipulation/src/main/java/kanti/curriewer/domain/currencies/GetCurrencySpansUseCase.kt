package kanti.curriewer.domain.currencies

import kanti.curriewer.data.model.currency.CurrencyRepository
import kanti.curriewer.data.model.currency.RangeAccuracy
import kanti.curriewer.shared.minusDay
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
	): Flow<CurrencySpan> = withContext(Dispatchers.IO) {
		return@withContext flow {
			currencyRepository.getAllCurrencyCodes().forEach { currencyCode ->
				launch {
					val currencySpan = getCurrencySpanUseCase(
						baseCurrencyCode = baseCurrencyCode,
						currencyCode = currencyCode,
						end = end,
						start = start,
						accuracy = accuracy
					)
					emit(currencySpan)
				}
			}
		}
	}
}