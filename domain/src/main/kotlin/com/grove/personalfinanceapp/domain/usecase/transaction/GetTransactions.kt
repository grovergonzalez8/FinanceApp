package com.grove.personalfinanceapp.domain.usecase.transaction

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetTransactions(
    private val transactionRepository: TransactionRepository,
) {

    operator fun invoke(userId: String): Flow<List<FinancialTransaction>> {
        val normalizedUserId = userId.normalizedText()
        requireNotBlank(normalizedUserId, "userId")

        return transactionRepository.observeTransactions(normalizedUserId)
    }
}
