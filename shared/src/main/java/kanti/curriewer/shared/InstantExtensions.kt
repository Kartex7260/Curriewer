package kanti.curriewer.shared

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus

fun Instant.minusDay(): Instant = minus(value = 1, unit = DateTimeUnit.DAY, timeZone = TimeZone.UTC)