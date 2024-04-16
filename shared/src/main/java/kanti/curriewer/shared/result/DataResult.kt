package kanti.curriewer.shared.result

internal typealias Err = DataError

sealed class DataResult<T, E : Err>(
	val value: T? = null,
	val error: E? = null
) {

	class Success<T, E : Err>(value: T) : DataResult<T, E>(value = value)

	class Error<T, E : Err>(
		error: E,
		value: T? = null
	) : DataResult<T, E>(value = value, error = error)
}