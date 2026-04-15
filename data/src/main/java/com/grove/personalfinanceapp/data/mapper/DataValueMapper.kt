package com.grove.personalfinanceapp.data.mapper

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

fun BigDecimal.toStorageString(): String = toPlainString()

fun String.toBigDecimal(): BigDecimal = BigDecimal(this)

fun Instant.toEpochMillisValue(): Long = toEpochMilli()

fun Long.toInstantValue(): Instant = Instant.ofEpochMilli(this)

fun LocalDate.toEpochDayValue(): Long = toEpochDay()

fun Long.toLocalDateValue(): LocalDate = LocalDate.ofEpochDay(this)
