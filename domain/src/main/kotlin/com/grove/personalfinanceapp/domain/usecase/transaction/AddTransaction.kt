package com.grove.personalfinanceapp.domain.usecase.transaction

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.common.requirePositive
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.common.UuidGenerator
import com.grove.personalfinanceapp.domain.usecase.common.normalizedCurrencyCode
import com.grove.personalfinanceapp.domain.usecase.common.normalizedOptionalText
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import com.grove.personalfinanceapp.domain.usecase.common.validationFailure
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant

class AddTransaction(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val clock: Clock,
    private val idGenerator: IdGenerator = UuidGenerator,
) {

    suspend operator fun invoke(input: Input): DomainResult<FinancialTransaction> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val title = input.title.normalizedText()
            val categoryId = input.categoryId.normalizedText()
            val currencyCode = input.currencyCode.normalizedCurrencyCode()

            requireNotBlank(userId, "userId")
            requireNotBlank(title, "title")
            requireNotBlank(categoryId, "categoryId")
            requirePositive(input.amount, "amount")

            val category = categoryRepository.observeCategories(userId).first()
                .firstOrNull { category -> category.id == categoryId }
                ?: return@runUseCase notFoundFailure(
                    entity = "Category",
                    identifier = categoryId,
                )

            if (category.type != input.type) {
                return@runUseCase validationFailure(
                    message = "Selected category type does not match the transaction type.",
                    field = "categoryId",
                )
            }

            val now = clock.instant()
            val transaction = FinancialTransaction(
                id = idGenerator.generate(),
                userId = userId,
                title = title,
                note = input.note.normalizedOptionalText(),
                amount = input.amount,
                currencyCode = currencyCode,
                type = input.type,
                categoryId = categoryId,
                occurredAt = input.occurredAt,
                createdAt = now,
                updatedAt = now,
            )

            transactionRepository.addTransaction(transaction)
        }
    }

    data class Input(
        val userId: String,
        val title: String,
        val note: String?,
        val amount: BigDecimal,
        val currencyCode: String,
        val type: TransactionType,
        val categoryId: String,
        val occurredAt: Instant,
    )
}
