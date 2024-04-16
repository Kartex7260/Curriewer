package kanti.curriewer.data.model.currency

enum class RangeAccuracy {

	DAY, HOUR, QUARTER_HOUR, MINUTE;

	val asAttribute: String get() = name.lowercase()

	companion object {

		val DEFAULT = DAY
	}
}