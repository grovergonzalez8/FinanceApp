package com.grove.personalfinanceapp.domain.model

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import java.time.Instant

data class Category(
    val id: String,
    val userId: String,
    val name: String,
    val type: TransactionType,
    val isDefault: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    init {
        requireNotBlank(id, "id")
        requireNotBlank(userId, "userId")
        requireNotBlank(name, "name")
    }
}
