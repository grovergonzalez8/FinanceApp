package com.grove.personalfinanceapp.domain.usecase.budget

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.BudgetPeriod
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.DateRange
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.usecase.common.validationFailure
import java.time.temporal.TemporalAdjusters

internal fun validateBudgetCategory(category: Category): DomainResult.Failure? {
    return if (category.type != TransactionType.EXPENSE) {
        validationFailure(
            message = "Budgets can only be created for expense categories.",
            field = "categoryId",
        )
    } else {
        null
    }
}

internal fun validateBudgetRange(
    period: BudgetPeriod,
    range: DateRange,
): DomainResult.Failure? {
    return when (period) {
        BudgetPeriod.WEEKLY -> {
            if (range.endDate != range.startDate.plusDays(6)) {
                validationFailure(
                    message = "Weekly budgets must span exactly seven days.",
                    field = "range",
                )
            } else {
                null
            }
        }

        BudgetPeriod.MONTHLY -> {
            val expectedEndDate = range.startDate.with(TemporalAdjusters.lastDayOfMonth())
            if (range.startDate.dayOfMonth != 1 || range.endDate != expectedEndDate) {
                validationFailure(
                    message = "Monthly budgets must cover a full calendar month.",
                    field = "range",
                )
            } else {
                null
            }
        }

        BudgetPeriod.CUSTOM -> null
    }
}
