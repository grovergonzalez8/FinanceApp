package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun observeTransactions(userId: String): Flow<List<FinancialTransaction>>

    suspend fun addTransaction(transaction: FinancialTransaction): DomainResult<FinancialTransaction>

    suspend fun updateTransaction(transaction: FinancialTransaction): DomainResult<FinancialTransaction>

    suspend fun deleteTransaction(transactionId: String): DomainResult<Unit>
}
