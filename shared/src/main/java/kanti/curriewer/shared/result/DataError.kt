package kanti.curriewer.shared.result

sealed interface DataError {

	val message: String?
	val throwable: Throwable?
}

data object NoError : DataError {

	override val message: String? = null
	override val throwable: Throwable? = null
}

class ValueIsNullError(
	override val message: String,
	override val throwable: Throwable? = null
) : DataError