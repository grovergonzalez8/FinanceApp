package com.grove.personalfinanceapp.domain.common

sealed interface DomainError {
    data class Validation(
        val message: String,
        val field: String? = null,
    ) : DomainError

    data class NotFound(
        val entity: String,
        val identifier: String,
    ) : DomainError

    data class Conflict(
        val message: String,
    ) : DomainError

    data class Storage(
        val message: String,
    ) : DomainError

    data class Unknown(
        val message: String? = null,
    ) : DomainError
}
