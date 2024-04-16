package kanti.curriewer.shared.result

interface RemoteError : DataError

data object NoConnectionError : RemoteError

data object NotAllowedUseEndpointError : RemoteError

data object NotFoundEndpointError : RemoteError

data object ValidationError : RemoteError

data object LimitBeenReached : RemoteError

data object ServerError : RemoteError

fun remoteErrorFromCode(code: Int): RemoteError? {
	return when (code) {
		403 -> NotAllowedUseEndpointError
		404 -> NotFoundEndpointError
		422 -> ValidationError
		429 -> LimitBeenReached
		500 -> ServerError
		else -> null
	}
}
