package com.grove.personalfinanceapp.domain.common

sealed interface DomainResult<out T> {
    data class Success<T>(
        val value: T,
    ) : DomainResult<T>

    data class Failure(
        val error: DomainError,
    ) : DomainResult<Nothing>
}
