package com.grove.personalfinanceapp.data.repository

import com.grove.personalfinanceapp.data.common.runDataOperation
import com.grove.personalfinanceapp.data.local.dao.BudgetDao
import com.grove.personalfinanceapp.data.local.dao.CategoryDao
import com.grove.personalfinanceapp.data.local.dao.SavingsGoalDao
import com.grove.personalfinanceapp.data.local.dao.TransactionDao
import com.grove.personalfinanceapp.data.local.dao.UserProfileDao
import com.grove.personalfinanceapp.data.mapper.toDomain
import com.grove.personalfinanceapp.data.preferences.UserPreferencesDataStoreSource
import com.grove.personalfinanceapp.domain.common.DomainResult
import com.grove.personalfinanceapp.domain.model.Budget
import com.grove.personalfinanceapp.domain.model.BudgetProgress
import com.grove.personalfinanceapp.domain.model.Category
import com.grove.personalfinanceapp.domain.model.CategorySpending
import com.grove.personalfinanceapp.domain.model.DashboardPeriod
import com.grove.personalfinanceapp.domain.model.DashboardSummary
import com.grove.personalfinanceapp.domain.model.DateRange
import com.grove.personalfinanceapp.domain.model.FinancialInsight
import com.grove.personalfinanceapp.domain.model.FinancialInsightType
import com.grove.personalfinanceapp.domain.model.FinancialTransaction
import com.grove.personalfinanceapp.domain.model.InsightSeverity
import com.grove.personalfinanceapp.domain.model.SavingsGoal
import com.grove.personalfinanceapp.domain.model.SavingsGoalPriority
import com.grove.personalfinanceapp.domain.model.TransactionType
import com.grove.personalfinanceapp.domain.model.UserPreferences
import com.grove.personalfinanceapp.domain.model.UserProfile
import com.grove.personalfinanceapp.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Clock
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao,
    private val budgetDao: BudgetDao,
    private val savingsGoalDao: SavingsGoalDao,
    private val userProfileDao: UserProfileDao,
    private val userPreferencesDataStoreSource: UserPreferencesDataStoreSource,
    private val clock: Clock,
) : DashboardRepository {

    override fun observeDashboardSummary(
        userId: String,
        period: DashboardPeriod,
    ): Flow<DashboardSummary> {
        val dashboardDataFlow = combine(
            transactionDao.observeTransactions(userId).map { entities ->
                entities.map { entity -> entity.toDomain() }
            },
            categoryDao.observeCategories(userId).map { entities ->
                entities.map { entity -> entity.toDomain() }
            },
            budgetDao.observeBudgets(userId).map { entities ->
                entities.map { entity -> entity.toDomain() }
            },
            savingsGoalDao.observeSavingsGoals(userId).map { entities ->
                entities.map { entity -> entity.toDomain() }
            },
            userProfileDao.observeUserProfile(userId).map { entity ->
                entity?.toDomain()
            },
        ) { transactions, categories, budgets, savingsGoals, profile ->
            DashboardData(
                transactions = transactions,
                categories = categories,
                budgets = budgets,
                savingsGoals = savingsGoals,
                profile = profile,
            )
        }

        return dashboardDataFlow
            .combine(userPreferencesDataStoreSource.observeUserPreferences(userId)) { data, preferences ->
                buildSummaryForCurrentPeriod(
                    userId = userId,
                    period = period,
                    transactions = data.transactions,
                    categories = data.categories,
                    budgets = data.budgets,
                    savingsGoals = data.savingsGoals,
                    profile = data.profile,
                    preferences = preferences,
                )
            }
            .distinctUntilChanged()
    }

    override suspend fun generateFinancialInsights(
        userId: String,
        period: DashboardPeriod,
    ): DomainResult<List<FinancialInsight>> {
        return runDataOperation(defaultMessage = "Unable to generate financial insights.") {
            val transactions = transactionDao.getTransactions(userId).map { entity -> entity.toDomain() }
            val categories = categoryDao.getCategories(userId).map { entity -> entity.toDomain() }
            val budgets = budgetDao.getBudgets(userId).map { entity -> entity.toDomain() }
            val savingsGoals = savingsGoalDao.getSavingsGoals(userId).map { entity -> entity.toDomain() }
            val profile = userProfileDao.getUserProfile(userId)?.toDomain()
            val preferences = userPreferencesDataStoreSource.getUserPreferences(userId)
            val weekStartsOn = preferences?.weekStartsOn ?: DEFAULT_WEEK_START
            val currentRange = calculateCurrentRange(period = period, weekStartsOn = weekStartsOn)
            val previousRange = calculatePreviousRange(period = period, currentRange = currentRange)
            val currentSummary = buildSummaryForRange(
                userId = userId,
                period = period,
                range = currentRange,
                transactions = transactions,
                categories = categories,
                budgets = budgets,
                savingsGoals = savingsGoals,
                profile = profile,
                preferences = preferences,
            )
            val previousSummary = buildSummaryForRange(
                userId = userId,
                period = period,
                range = previousRange,
                transactions = transactions,
                categories = categories,
                budgets = budgets,
                savingsGoals = savingsGoals,
                profile = profile,
                preferences = preferences,
            )

            DomainResult.Success(
                value = buildInsights(
                    userId = userId,
                    currentSummary = currentSummary,
                    previousSummary = previousSummary,
                    generatedAt = clock.instant(),
                ),
            )
        }
    }

    private fun buildSummaryForCurrentPeriod(
        userId: String,
        period: DashboardPeriod,
        transactions: List<FinancialTransaction>,
        categories: List<Category>,
        budgets: List<Budget>,
        savingsGoals: List<SavingsGoal>,
        profile: UserProfile?,
        preferences: UserPreferences?,
    ): DashboardSummary {
        val weekStartsOn = preferences?.weekStartsOn ?: DEFAULT_WEEK_START
        val range = calculateCurrentRange(period = period, weekStartsOn = weekStartsOn)

        return buildSummaryForRange(
            userId = userId,
            period = period,
            range = range,
            transactions = transactions,
            categories = categories,
            budgets = budgets,
            savingsGoals = savingsGoals,
            profile = profile,
            preferences = preferences,
        )
    }

    private fun buildSummaryForRange(
        userId: String,
        period: DashboardPeriod,
        range: DateRange,
        transactions: List<FinancialTransaction>,
        categories: List<Category>,
        budgets: List<Budget>,
        savingsGoals: List<SavingsGoal>,
        profile: UserProfile?,
        preferences: UserPreferences?,
    ): DashboardSummary {
        val categoryById = categories.associateBy { category -> category.id }
        val transactionsInRange = transactions.filter { transaction ->
            range.contains(transaction.occurredAt.toLocalDate(zoneId = clock.zone))
        }
        val expenseTransactions = transactionsInRange.filter { transaction ->
            transaction.type == TransactionType.EXPENSE
        }
        val totalIncome = transactionsInRange
            .filter { transaction -> transaction.type == TransactionType.INCOME }
            .sumAmounts()
        val totalExpenses = expenseTransactions.sumAmounts()
        val currencyCode = resolveCurrencyCode(
            transactions = transactionsInRange,
            budgets = budgets,
            savingsGoals = savingsGoals,
            profile = profile,
            preferences = preferences,
        )

        return DashboardSummary(
            userId = userId,
            period = period,
            range = range,
            currencyCode = currencyCode,
            totalIncome = totalIncome,
            totalExpenses = totalExpenses,
            transactionCount = transactionsInRange.size,
            budgetProgress = buildBudgetProgress(
                summaryRange = range,
                budgets = budgets,
                expenseTransactions = expenseTransactions,
                categoryById = categoryById,
            ),
            topSpendingCategories = buildTopSpendingCategories(
                expenseTransactions = expenseTransactions,
                categoryById = categoryById,
            ),
            prioritySavingsGoals = buildPrioritySavingsGoals(
                savingsGoals = savingsGoals,
                prioritize = preferences?.prioritizeSavingsGoals != false,
            ),
        )
    }

    private fun buildBudgetProgress(
        summaryRange: DateRange,
        budgets: List<Budget>,
        expenseTransactions: List<FinancialTransaction>,
        categoryById: Map<String, Category>,
    ): List<BudgetProgress> {
        return budgets
            .filter { budget -> budget.range.overlaps(summaryRange) }
            .map { budget ->
                val spentAmount = expenseTransactions
                    .filter { transaction -> transaction.categoryId == budget.categoryId }
                    .filter { transaction ->
                        budget.range.contains(transaction.occurredAt.toLocalDate(zoneId = clock.zone))
                    }
                    .sumAmounts()

                BudgetProgress(
                    budgetId = budget.id,
                    categoryId = budget.categoryId,
                    categoryName = categoryById[budget.categoryId]?.name ?: UNKNOWN_CATEGORY_NAME,
                    limitAmount = budget.limitAmount,
                    spentAmount = spentAmount,
                    currencyCode = budget.currencyCode,
                )
            }
            .sortedByDescending { budgetProgress -> budgetProgress.spentAmount }
    }

    private fun buildTopSpendingCategories(
        expenseTransactions: List<FinancialTransaction>,
        categoryById: Map<String, Category>,
    ): List<CategorySpending> {
        return expenseTransactions
            .groupBy { transaction -> transaction.categoryId }
            .map { (categoryId, categoryTransactions) ->
                CategorySpending(
                    categoryId = categoryId,
                    categoryName = categoryById[categoryId]?.name ?: UNKNOWN_CATEGORY_NAME,
                    totalAmount = categoryTransactions.sumAmounts(),
                    currencyCode = categoryTransactions.first().currencyCode,
                    transactionCount = categoryTransactions.size,
                )
            }
            .sortedByDescending { categorySpending -> categorySpending.totalAmount }
            .take(MAX_TOP_SPENDING_CATEGORIES)
    }

    private fun buildPrioritySavingsGoals(
        savingsGoals: List<SavingsGoal>,
        prioritize: Boolean,
    ): List<SavingsGoal> {
        if (!prioritize) {
            return emptyList()
        }

        return savingsGoals
            .sortedWith(
                compareByDescending<SavingsGoal> { goal -> goal.priority.rank() }
                    .thenBy { goal -> goal.targetDate ?: LocalDate.MAX }
                    .thenByDescending { goal -> goal.currentAmount },
            )
            .take(MAX_PRIORITY_GOALS)
    }

    private fun resolveCurrencyCode(
        transactions: List<FinancialTransaction>,
        budgets: List<Budget>,
        savingsGoals: List<SavingsGoal>,
        profile: UserProfile?,
        preferences: UserPreferences?,
    ): String {
        return preferences?.currencyCode
            ?: profile?.currencyCode
            ?: transactions.firstOrNull()?.currencyCode
            ?: budgets.firstOrNull()?.currencyCode
            ?: savingsGoals.firstOrNull()?.currencyCode
            ?: DEFAULT_CURRENCY_CODE
    }

    private fun calculateCurrentRange(
        period: DashboardPeriod,
        weekStartsOn: DayOfWeek,
    ): DateRange {
        val today = LocalDate.now(clock)

        return when (period) {
            DashboardPeriod.WEEK -> {
                val startDate = today.with(TemporalAdjusters.previousOrSame(weekStartsOn))
                DateRange(
                    startDate = startDate,
                    endDate = startDate.plusDays(DAYS_IN_WEEK - 1L),
                )
            }

            DashboardPeriod.MONTH -> {
                val startDate = today.withDayOfMonth(1)
                DateRange(
                    startDate = startDate,
                    endDate = startDate.with(TemporalAdjusters.lastDayOfMonth()),
                )
            }

            DashboardPeriod.YEAR -> {
                val startDate = today.withDayOfYear(1)
                DateRange(
                    startDate = startDate,
                    endDate = today.withDayOfYear(today.lengthOfYear()),
                )
            }
        }
    }

    private fun calculatePreviousRange(
        period: DashboardPeriod,
        currentRange: DateRange,
    ): DateRange {
        return when (period) {
            DashboardPeriod.WEEK -> DateRange(
                startDate = currentRange.startDate.minusDays(DAYS_IN_WEEK.toLong()),
                endDate = currentRange.startDate.minusDays(1),
            )

            DashboardPeriod.MONTH -> {
                val previousMonthDate = currentRange.startDate.minusMonths(1)
                val startDate = previousMonthDate.withDayOfMonth(1)
                DateRange(
                    startDate = startDate,
                    endDate = startDate.with(TemporalAdjusters.lastDayOfMonth()),
                )
            }

            DashboardPeriod.YEAR -> {
                val previousYearDate = currentRange.startDate.minusYears(1)
                val startDate = previousYearDate.withDayOfYear(1)
                DateRange(
                    startDate = startDate,
                    endDate = previousYearDate.withDayOfYear(previousYearDate.lengthOfYear()),
                )
            }
        }
    }

    private fun buildInsights(
        userId: String,
        currentSummary: DashboardSummary,
        previousSummary: DashboardSummary,
        generatedAt: Instant,
    ): List<FinancialInsight> {
        val insights = mutableListOf<FinancialInsight>()

        addSpendingSpikeInsight(
            insights = insights,
            userId = userId,
            currentSummary = currentSummary,
            previousSummary = previousSummary,
            generatedAt = generatedAt,
        )
        addBudgetRiskInsights(
            insights = insights,
            userId = userId,
            summary = currentSummary,
            generatedAt = generatedAt,
        )
        addCashFlowInsight(
            insights = insights,
            userId = userId,
            summary = currentSummary,
            generatedAt = generatedAt,
        )
        addCategoryConcentrationInsight(
            insights = insights,
            userId = userId,
            summary = currentSummary,
            generatedAt = generatedAt,
        )
        addSavingsProgressInsight(
            insights = insights,
            userId = userId,
            summary = currentSummary,
            generatedAt = generatedAt,
        )

        return insights
            .sortedByDescending { insight -> insight.severity.rank() }
            .take(MAX_INSIGHTS)
    }

    private fun addSpendingSpikeInsight(
        insights: MutableList<FinancialInsight>,
        userId: String,
        currentSummary: DashboardSummary,
        previousSummary: DashboardSummary,
        generatedAt: Instant,
    ) {
        if (previousSummary.totalExpenses <= BigDecimal.ZERO) {
            return
        }

        val ratio = currentSummary.totalExpenses.divide(
            previousSummary.totalExpenses,
            4,
            RoundingMode.HALF_UP,
        )

        if (ratio < SPENDING_SPIKE_THRESHOLD) {
            return
        }

        val increaseAmount = currentSummary.totalExpenses.subtract(previousSummary.totalExpenses)
        insights += createInsight(
            userId = userId,
            type = FinancialInsightType.SPENDING_SPIKE,
            severity = if (ratio >= CRITICAL_SPENDING_SPIKE_THRESHOLD) {
                InsightSeverity.CRITICAL
            } else {
                InsightSeverity.WARNING
            },
            title = "Spending increased this ${currentSummary.period.name.lowercase()}",
            message = "Expenses are ${toPercentString(ratio.subtract(BigDecimal.ONE))} above the previous period.",
            generatedAt = generatedAt,
            highlightAmount = increaseAmount,
            currencyCode = currentSummary.currencyCode,
        )
    }

    private fun addBudgetRiskInsights(
        insights: MutableList<FinancialInsight>,
        userId: String,
        summary: DashboardSummary,
        generatedAt: Instant,
    ) {
        summary.budgetProgress
            .filter { progress -> progress.utilizationRatio >= BUDGET_WARNING_THRESHOLD }
            .forEach { progress ->
                insights += createInsight(
                    userId = userId,
                    type = FinancialInsightType.BUDGET_RISK,
                    severity = if (progress.isExceeded) {
                        InsightSeverity.CRITICAL
                    } else {
                        InsightSeverity.WARNING
                    },
                    title = "${progress.categoryName} budget is under pressure",
                    message = if (progress.isExceeded) {
                        "Spending is already above the budget limit for this category."
                    } else {
                        "This category has used ${toPercentString(progress.utilizationRatio)} of its budget."
                    },
                    generatedAt = generatedAt,
                    relatedCategoryId = progress.categoryId,
                    highlightAmount = progress.spentAmount,
                    currencyCode = progress.currencyCode,
                )
            }
    }

    private fun addCashFlowInsight(
        insights: MutableList<FinancialInsight>,
        userId: String,
        summary: DashboardSummary,
        generatedAt: Instant,
    ) {
        if (summary.netBalance >= BigDecimal.ZERO) {
            return
        }

        insights += createInsight(
            userId = userId,
            type = FinancialInsightType.CASH_FLOW_TREND,
            severity = InsightSeverity.WARNING,
            title = "Expenses are outpacing income",
            message = "The current period balance is negative.",
            generatedAt = generatedAt,
            highlightAmount = summary.netBalance.abs(),
            currencyCode = summary.currencyCode,
        )
    }

    private fun addCategoryConcentrationInsight(
        insights: MutableList<FinancialInsight>,
        userId: String,
        summary: DashboardSummary,
        generatedAt: Instant,
    ) {
        if (summary.totalExpenses <= BigDecimal.ZERO) {
            return
        }

        val topCategory = summary.topSpendingCategories.firstOrNull() ?: return
        val concentrationRatio = topCategory.totalAmount.divide(
            summary.totalExpenses,
            4,
            RoundingMode.HALF_UP,
        )

        if (concentrationRatio < CATEGORY_CONCENTRATION_THRESHOLD) {
            return
        }

        insights += createInsight(
            userId = userId,
            type = FinancialInsightType.CATEGORY_CONCENTRATION,
            severity = InsightSeverity.INFO,
            title = "${topCategory.categoryName} leads spending",
            message = "This category accounts for ${toPercentString(concentrationRatio)} of expenses.",
            generatedAt = generatedAt,
            relatedCategoryId = topCategory.categoryId,
            highlightAmount = topCategory.totalAmount,
            currencyCode = topCategory.currencyCode,
        )
    }

    private fun addSavingsProgressInsight(
        insights: MutableList<FinancialInsight>,
        userId: String,
        summary: DashboardSummary,
        generatedAt: Instant,
    ) {
        val goal = summary.prioritySavingsGoals.firstOrNull() ?: return
        val completionRatio = goal.currentAmount.divide(
            goal.targetAmount,
            4,
            RoundingMode.HALF_UP,
        )

        if (completionRatio < SAVINGS_PROGRESS_THRESHOLD && !goal.isCompleted) {
            return
        }

        insights += createInsight(
            userId = userId,
            type = FinancialInsightType.SAVINGS_PROGRESS,
            severity = InsightSeverity.INFO,
            title = if (goal.isCompleted) {
                "${goal.name} reached its target"
            } else {
                "${goal.name} is moving forward"
            },
            message = if (goal.isCompleted) {
                "The savings goal target has been completed."
            } else {
                "This goal is ${toPercentString(completionRatio)} funded."
            },
            generatedAt = generatedAt,
            highlightAmount = goal.currentAmount,
            currencyCode = goal.currencyCode,
        )
    }

    private fun createInsight(
        userId: String,
        type: FinancialInsightType,
        severity: InsightSeverity,
        title: String,
        message: String,
        generatedAt: Instant,
        relatedCategoryId: String? = null,
        highlightAmount: BigDecimal? = null,
        currencyCode: String? = null,
    ): FinancialInsight {
        return FinancialInsight(
            id = UUID.randomUUID().toString(),
            userId = userId,
            type = type,
            severity = severity,
            title = title,
            message = message,
            generatedAt = generatedAt,
            relatedCategoryId = relatedCategoryId,
            highlightAmount = highlightAmount,
            currencyCode = currencyCode,
        )
    }

    private fun Iterable<FinancialTransaction>.sumAmounts(): BigDecimal {
        return fold(BigDecimal.ZERO) { total, transaction ->
            total.add(transaction.amount)
        }
    }

    private fun Instant.toLocalDate(zoneId: ZoneId): LocalDate {
        return atZone(zoneId).toLocalDate()
    }

    private fun DateRange.overlaps(other: DateRange): Boolean {
        return !(endDate.isBefore(other.startDate) || startDate.isAfter(other.endDate))
    }

    private fun SavingsGoalPriority.rank(): Int {
        return when (this) {
            SavingsGoalPriority.HIGH -> 3
            SavingsGoalPriority.MEDIUM -> 2
            SavingsGoalPriority.LOW -> 1
        }
    }

    private fun InsightSeverity.rank(): Int {
        return when (this) {
            InsightSeverity.CRITICAL -> 3
            InsightSeverity.WARNING -> 2
            InsightSeverity.INFO -> 1
        }
    }

    private fun toPercentString(value: BigDecimal): String {
        return value
            .multiply(PERCENT_MULTIPLIER)
            .setScale(0, RoundingMode.HALF_UP)
            .toPlainString() + "%"
    }

    private data class DashboardData(
        val transactions: List<FinancialTransaction>,
        val categories: List<Category>,
        val budgets: List<Budget>,
        val savingsGoals: List<SavingsGoal>,
        val profile: UserProfile?,
    )

    private companion object {
        private const val DEFAULT_CURRENCY_CODE = "USD"
        private const val UNKNOWN_CATEGORY_NAME = "Unknown category"
        private const val MAX_TOP_SPENDING_CATEGORIES = 5
        private const val MAX_PRIORITY_GOALS = 3
        private const val MAX_INSIGHTS = 5
        private const val DAYS_IN_WEEK = 7
        private val DEFAULT_WEEK_START = DayOfWeek.MONDAY
        private val PERCENT_MULTIPLIER = BigDecimal("100")
        private val SPENDING_SPIKE_THRESHOLD = BigDecimal("1.15")
        private val CRITICAL_SPENDING_SPIKE_THRESHOLD = BigDecimal("1.30")
        private val BUDGET_WARNING_THRESHOLD = BigDecimal("0.80")
        private val CATEGORY_CONCENTRATION_THRESHOLD = BigDecimal("0.40")
        private val SAVINGS_PROGRESS_THRESHOLD = BigDecimal("0.50")
    }
}
