package com.grove.personalfinanceapp.domain.usecase.category

import com.grove.personalfinanceapp.domain.common.requireNotBlank
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import com.grove.personalfinanceapp.domain.usecase.common.normalizedText
import kotlinx.coroutines.flow.Flow

class GetCategories(
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(userId: String): Flow<List<Category>> {
        val normalizedUserId = userId.normalizedText()
        requireNotBlank(normalizedUserId, "userId")

        return categoryRepository.observeCategories(normalizedUserId)
    }
}
