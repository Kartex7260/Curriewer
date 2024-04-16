package kanti.curriewer.shared.result

interface RemoteError : DataError

data object NoConnectionError : RemoteError
