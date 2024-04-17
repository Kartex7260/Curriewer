package kanti.curriewer.shared.result

interface RemoteError : DataError

data class NoConnectionError(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

data class NotAllowedUseEndpointError(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

data class NotFoundEndpointError(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

data class ValidationError(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

data class LimitBeenReached(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

data class ServerError(
	override val message: String? = null,
	override val throwable: Throwable? = null
) : RemoteError

fun remoteErrorFromCode(code: Int): RemoteError? {
	return when (code) {
		403 -> NotAllowedUseEndpointError()
		404 -> NotFoundEndpointError()
		422 -> ValidationError()
		429 -> LimitBeenReached()
		500 -> ServerError()
		else -> null
	}
}
