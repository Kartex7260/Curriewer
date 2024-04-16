package kanti.curriewer.shared.result

inline fun <I, O, IErr : Err> DataResult<I, IErr>.runIfNotError(
	block: (I) -> DataResult<O, Err>
): DataResult<O, Err> {
	val error = error
	if (error != null)
		return DataResult.Error(error)

	val value = value ?: return DataResult
		.Error(ValueIsNullError(this.javaClass.name))

	return block(value)
}

inline fun <I, IErr : Err> DataResult<I, IErr>.alsoIfNotError(
	block: (I) -> Unit
) {
	if (error != null)
		return

	block(value ?: return)
}