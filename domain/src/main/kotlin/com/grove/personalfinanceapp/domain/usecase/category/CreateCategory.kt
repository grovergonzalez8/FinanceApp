package com.grove.personalfinanceapp.domain.usecase.category

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.usecase.common.IdGenerator
import com.grove.personalfinanceapp.domain.usecase.common.UuidGenerator
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import com.grove.personalfinanceapp.domain.usecase.common.runUseCase
import java.time.Clock

class CreateCategory(
    private val categoryRepository: CategoryRepository,
    private val clock: Clock,
    private val idGenerator: IdGenerator = UuidGenerator,
) {

    suspend operator fun invoke(input: Input): DomainResult<Category> {
        return runUseCase {
            val userId = input.userId.normalizedText()
            val name = input.name.normalizedText()

            requireNotBlank(userId, "userId")
            requireNotBlank(name, "name")

            val now = clock.instant()
            val category = Category(
                id = idGenerator.generate(),
                userId = userId,
                name = name,
                type = input.type,
                isDefault = false,
                createdAt = now,
                updatedAt = now,
            )

            categoryRepository.createCategory(category)
        }
    }

    data class Input(
        val userId: String,
        val name: String,
        val type: TransactionType,
    )
}
