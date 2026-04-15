package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.CategoryDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.mapper.toEntity
import com.grove.personalfinanceapp.domain.common.DomainError
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun observeCategories(userId: String): Flow<List<Category>> {
        return categoryDao.observeCategories(userId).map { entities ->
            entities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun createCategory(category: Category): DomainResult<Category> {
        return runDataOperation(defaultMessage = "Unable to create category.") {
            categoryDao.insertCategory(category.toEntity())
            DomainResult.Success(category)
        }
    }

    override suspend fun updateCategory(category: Category): DomainResult<Category> {
        return runDataOperation(defaultMessage = "Unable to update category.") {
            val rowsUpdated = categoryDao.updateCategory(category.toEntity())
            if (rowsUpdated == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "Category",
                        identifier = category.id,
                    ),
                )
            } else {
                DomainResult.Success(category)
            }
        }
    }

    override suspend fun deleteCategory(categoryId: String): DomainResult<Unit> {
        return runDataOperation(defaultMessage = "Unable to delete category.") {
            val rowsDeleted = categoryDao.deleteCategoryById(categoryId)
            if (rowsDeleted == 0) {
                DomainResult.Failure(
                    error = DomainError.NotFound(
                        entity = "Category",
                        identifier = categoryId,
                    ),
                )
            } else {
                DomainResult.Success(Unit)
            }
        }
    }
}
