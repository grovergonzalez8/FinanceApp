package com.grove.personalfinanceapp.domain.usecase.transaction

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.notFoundFailure
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import kotlinx.coroutines.flow.first

class DeleteTransaction(
    private val transactionRepository: TransactionRepository,
) {

    suspend operator fun invoke(input: Input): DomainResult<Unit> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val transactionId = input.transactionId.normalizedText()

            requireNotBlank(userId, "userId")
            requireNotBlank(transactionId, "transactionId")

            val transactionExists = transactionRepository.observeTransactions(userId).first()
                .any { transaction -> transaction.id == transactionId }

            if (!transactionExists) {
                return@runUseCase notFoundFailure(
                    entity = "FinancialTransaction",
                    identifier = transactionId,
                )
            }

            transactionRepository.deleteTransaction(transactionId)
        }
    }

    data class Input(
        val userId: String,
        val transactionId: String,
    )
}
