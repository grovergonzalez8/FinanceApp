package com.grove.personalfinanceapp.domain.repository

import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun observeCategories(userId: String): Flow<List<Category>>

    suspend fun createCategory(category: Category): DomainResult<Category>

    suspend fun updateCategory(category: Category): DomainResult<Category>

    suspend fun deleteCategory(categoryId: String): DomainResult<Unit>
}
