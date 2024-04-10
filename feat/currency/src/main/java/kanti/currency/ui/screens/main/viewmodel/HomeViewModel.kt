package kanti.currency.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import kanti.curriewer.ui.components.CurrencyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
) : ViewModel() {

	private val _baseCurrency = MutableStateFlow(CurrencyData())
	val baseCurrency = _baseCurrency.asStateFlow()

	private val _curriencies = MutableStateFlow<List<CurrencyData>>(listOf())
	val curriencies: StateFlow<List<CurrencyData>> = _curriencies.asStateFlow()
}