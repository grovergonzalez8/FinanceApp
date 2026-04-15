package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.TransactionDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.mapper.toEntity
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
) : TransactionRepository {

    override fun observeTransactions(userId: String): Flow<List<FinancialTransaction>> {
        return transactionDao.observeTransactions(userId).map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun addTransaction(
        transaction: FinancialTransaction,
    ): DomainResult<FinancialTransaction> {
        return runDataOperation(defaultMessage = "Unable to add transaction.") {
            transactionDao.insertTransaction(transaction.toEntity())
            DomainResult.Success(transaction)
        }
    }

    override suspend fun updateTransaction(
        transaction: FinancialTransaction,
    ): DomainResult<FinancialTransaction> {
        return runDataOperation(defaultMessage = "Unable to update transaction.") {
            val rowsUpdated = transactionDao.updateTransaction(transaction.toEntity())
            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "FinancialTransaction",
                        identifier = transaction.id,
                    ),
                )
            } else {
                DomainResult.Success(transaction)
            }
        }
    }

    override suspend fun deleteTransaction(transactionId: String): DomainResult<Unit> {
        return runDataOperation(defaultMessage = "Unable to delete transaction.") {
            val rowsDeleted = transactionDao.deleteTransactionById(transactionId)
            if (rowsDeleted == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "FinancialTransaction",
                        identifier = transactionId,
                    ),
                )
            } else {
                DomainResult.Success(Unit)
            }
        }
    }
}
