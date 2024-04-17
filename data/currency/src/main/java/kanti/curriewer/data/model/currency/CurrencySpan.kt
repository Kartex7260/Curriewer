package kanti.curriewer.data.model.currency

data class CurrencySpan(
	val code: String = "",
	val title: String = "",
	val value: Double = 0.0,
	val dynamic: Double = 0.0,
	val dynamicInPercent: Float = 0.0f
)
