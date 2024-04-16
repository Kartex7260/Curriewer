package kanti.curriewer.shared.result

sealed interface DataError

data object NoError : DataError

class ValueIsNullError(message: String) : DataError