package com.grove.personalfinanceapp.domain.usecase.common

import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import java.util.Locale

internal inline fun <T> runUseCase(block: () -> DomainResult<T>): DomainResult<T> {
    return try {
        block()
    } catch (exception: IllegalArgumentException) {
        DomainResult.Failure(
            error = DomainError.Validation(
                message = exception.message ?: "Invalid input.",
            ),
        )
    }
}

internal fun validationFailure(
    message: String,
    field: String? = null,
): DomainResult.Failure {
    return DomainResult.Failure(
        error = DomainError.Validation(
            message = message,
            field = field,
        ),
    )
}

internal fun notFoundFailure(
    entity: String,
    identifier: String,
): DomainResult.Failure {
    return DomainResult.Failure(
        error = DomainError.NotFound(
            entity = entity,
            identifier = identifier,
        ),
    )
}

internal fun conflictFailure(message: String): DomainResult.Failure {
    return DomainResult.Failure(
        error = DomainError.Conflict(message = message),
    )
}

internal fun String.normalizedText(): String = trim()

internal fun String?.normalizedOptionalText(): String? {
    return this?.trim()?.takeIf { value -> value.isNotEmpty() }
}

internal fun String.normalizedCurrencyCode(): String = trim().uppercase(Locale.ROOT)

internal fun String?.normalizedOptionalCurrencyCode(): String? {
    return normalizedOptionalText()?.uppercase(Locale.ROOT)
}
