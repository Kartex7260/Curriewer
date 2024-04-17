package kanti.curriewer.shared.result

interface LocalError : DataError

data class NotFoundError(
	override val message: String?,
	override val throwable: Throwable?
) : LocalError