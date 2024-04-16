package kanti.curriewer.shared.result

inline fun <I, O> DataResult<I, Err>.ifNoError(
	block: (I) -> DataResult<O, Err>
): DataResult<O, Err> {
	val error = error
	if (error != null)
		return DataResult.Error(error)

	val value = value ?: return DataResult
		.Error(ValueIsNullError(this.javaClass.name))

	return block(value)
}