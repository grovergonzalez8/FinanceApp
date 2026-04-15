package com.grove.personalfinanceapp.data.repository;

import com.grove.personalfinanceapp.data.local.dao.CategoryDao;
import com.grove.personalfinanceapp.domain.common.DomainError;
import com.grove.personalfinanceapp.domain.common.DomainResult;
import com.grove.personalfinanceapp.domain.model.Category;
import com.grove.personalfinanceapp.domain.repository.CategoryRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00102\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/grove/personalfinanceapp/data/repository/CategoryRepositoryImpl;", "Lcom/grove/personalfinanceapp/domain/repository/CategoryRepository;", "categoryDao", "Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;", "(Lcom/grove/personalfinanceapp/data/local/dao/CategoryDao;)V", "createCategory", "Lcom/grove/personalfinanceapp/domain/common/DomainResult;", "Lcom/grove/personalfinanceapp/domain/model/Category;", "category", "(Lcom/grove/personalfinanceapp/domain/model/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCategory", "", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeCategories", "Lkotlinx/coroutines/flow/Flow;", "", "userId", "updateCategory", "data_debug"})
public final class CategoryRepositoryImpl implements com.grove.personalfinanceapp.domain.repository.CategoryRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.grove.personalfinanceapp.data.local.dao.CategoryDao categoryDao = null;
    
    @javax.inject.Inject()
    public CategoryRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.data.local.dao.CategoryDao categoryDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.grove.personalfinanceapp.domain.model.Category>> observeCategories(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createCategory(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.Category>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateCategory(@org.jetbrains.annotations.NotNull()
    com.grove.personalfinanceapp.domain.model.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<com.grove.personalfinanceapp.domain.model.Category>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.grove.personalfinanceapp.domain.common.DomainResult<kotlin.Unit>> $completion) {
        return null;
    }
}