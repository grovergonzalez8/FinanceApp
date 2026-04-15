package com.grove.personalfinanceapp.data.common

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import kotlinx.coroutines.CancellationException
import java.io.IOException

suspend inline fun <T> runDataOperation(
    defaultMessage: String,
    crossinline block: suspend () -> DomainResult<T>,
): DomainResult<T> {
    return try {
        block()
    } catch (exception: Exception) {
        if (exception is CancellationException) {
            throw exception
        }
        DomainResult.Failure(exception.toDomainError(defaultMessage))
    }
}

fun Throwable.toDomainError(defaultMessage: String): DomainError {
    return when (this) {
        is SQLiteConstraintException -> DomainError.Conflict(message ?: defaultMessage)
        is IOException -> DomainError.Storage(message ?: defaultMessage)
        is SQLiteException -> DomainError.Storage(message ?: defaultMessage)
        is IllegalArgumentException -> DomainError.Validation(
            message = message ?: defaultMessage,
        )
        else -> DomainError.Unknown(message ?: defaultMessage)
    }
}
