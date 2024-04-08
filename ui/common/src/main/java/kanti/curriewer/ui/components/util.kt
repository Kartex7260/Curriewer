package kanti.curriewer.ui.components

import java.util.Locale

fun DynamicData.isUpDynamic() = dynamic.isUpDynamic()

fun Double.isUpDynamic() = this >= 0

fun Float.removeMinus() = if (this < 0) -this else this

fun Double.formatToString(): String {
	return "%,.2f".format(Locale.ENGLISH, this)
}

fun Float.formatToString(): String {
	return "%,.2f".format(Locale.ENGLISH, this)
}