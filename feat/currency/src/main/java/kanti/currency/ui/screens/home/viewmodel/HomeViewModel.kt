package kanti.currency.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kanti.currency.ui.util.toCurrencyData
import kanti.curriewer.data.app.AppDataRepository
import kanti.curriewer.domain.currencies.GetCurrencySpansUseCase
import kanti.curriewer.ui.components.CurrencyData
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
	getCurrencySpansUseCase: GetCurrencySpansUseCase
) : ViewModel() {

	val baseCurrency: StateFlow<String> = appDataRepository.appData
		.map { it.baseCurrencyCode }
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.Eagerly,
			initialValue = ""
		)

	val currencies: StateFlow<List<CurrencyData>> = appDataRepository.appData
		.map { appData ->
			getCurrencySpansUseCase(
				baseCurrencyCode = appData.baseCurrencyCode
			).map { it.toCurrencyData() }
				.toList()
				.sortedBy { it.code }
		}
		.flowOn(Dispatchers.Default)
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.Eagerly,
			initialValue = listOf()
		)

}