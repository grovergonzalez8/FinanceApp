package com.grove.personalfinanceapp.domain.model

import java.time.LocalDate

data class DateRange(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    init {
        require(!endDate.isBefore(startDate)) { "endDate cannot be before startDate." }
    }

    fun contains(date: LocalDate): Boolean {
        return !date.isBefore(startDate) && !date.isAfter(endDate)
    }
}
