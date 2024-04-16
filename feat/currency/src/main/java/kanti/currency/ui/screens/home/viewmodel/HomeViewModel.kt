package kanti.currency.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kanti.currency.ui.util.toCurrencySpan
import kanti.curriewer.data.app.AppDataRepository
import kanti.curriewer.data.model.currency.CurrencyRepository
import kanti.curriewer.domain.currencies.GetCurrencySpansUseCase
import kanti.curriewer.shared.result.DataError
import kanti.curriewer.shared.result.DataResult
import kanti.curriewer.shared.result.runIfNotError
import kanti.curriewer.ui.components.CurrencyUiState
import kanti.curriewer.ui.components.CurrencySpanUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class HomeViewModel @Inject constructor(
	appDataRepository: AppDataRepository,
	currencyRepository: CurrencyRepository,
	getCurrencySpansUseCase: GetCurrencySpansUseCase
) : ViewModel() {

	val baseCurrency: StateFlow<DataResult<CurrencyUiState, DataError>> = appDataRepository.appData
		.map { appData ->
			val titleResult = currencyRepository.getTitleByCode(currencyCode = appData.baseCurrencyCode)
			titleResult.runIfNotError { title ->
				DataResult.Success(CurrencyUiState(title = title, code = appData.baseCurrencyCode))
			}
		}
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.Eagerly,
			initialValue = DataResult.Success(CurrencyUiState(title = "", code = ""))
		)

	val currencies: StateFlow<DataResult<List<CurrencySpanUiState>, DataError>> = appDataRepository.appData
		.map { appData ->
			val result = getCurrencySpansUseCase(
				baseCurrencyCode = appData.baseCurrencyCode
			)

			result.runIfNotError { currencySpans ->
				val currencies = currencySpans.map { it.toCurrencySpan() }
					.toList()
					.sortedBy { it.data.code }
				DataResult.Success(currencies)
			}
		}
		.flowOn(Dispatchers.Default)
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.Eagerly,
			initialValue = DataResult.Success(listOf())
		)

}