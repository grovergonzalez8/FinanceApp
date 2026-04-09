# FIN-APP-001 — Personal Finance Android App

## Project Overview
This project consists of building an Android native personal finance application from scratch.

The application must allow each user to manage personal finances in a structured, personalized, and scalable way. It should support financial tracking, category management, budgets, savings goals, user preferences, and dashboard summaries.

The implementation must follow real Clean Architecture principles and be designed as a production-grade MVP, not as a disposable prototype.

---

## Product Goal
Build an Android personal finance app that is:

- personalized per user
- architecturally clean
- maintainable
- scalable
- testable
- visually modern and professional

The app must feel useful and organized for a real user who wants visibility and control over personal money management.

---

## Core Functional Goals
The app must support, at minimum, the following functional areas:

- user onboarding
- local user profile configuration
- financial transaction management
- custom categories
- budgets by category
- savings goals
- dashboard summaries
- user preferences persistence
- basic financial insights
- profile and settings management

---

## Personalization Goals
The app must not behave like a generic expense tracker. It must adapt to each user through:

- custom categories
- customizable budgets
- configurable dashboard preferences
- user currency preferences
- savings goal prioritization
- persistent local settings
- financial summaries relevant to the user's own data

---

## Architecture Goal
The project must use strict Clean Architecture with clear separation between:

- Presentation
- Domain
- Data

### Presentation
Responsible for:
- Compose UI
- screen rendering
- ViewModels
- navigation
- UI state handling

### Domain
Responsible for:
- core business models
- business rules
- use cases
- repository contracts
- validation logic that belongs to business behavior

### Data
Responsible for:
- Room entities
- DAO interfaces
- persistence
- mappers
- repository implementations
- preferences persistence with DataStore

---

## Technical Stack
The app must use the following stack unless a strong reason justifies otherwise:

- Kotlin
- Jetpack Compose
- Material 3
- Hilt
- Room
- DataStore
- Kotlin Coroutines
- Flow / StateFlow
- Navigation Compose

Optional only if justified:
- Timber
- Coil

---

## Design Direction
The visual design must use:

- dark blue as the primary base color
- modern layout
- minimal and premium visual style
- clear hierarchy
- reusable components
- support for well-defined loading, error, success, and empty states

The UI should communicate:
- clarity
- trust
- control
- organization

---

## Core Domain Models
The project should include, at minimum, the following domain models:

- UserProfile
- UserPreferences
- FinancialTransaction
- TransactionType
- Category
- Budget
- SavingsGoal
- DashboardSummary
- FinancialInsight

Additional support models may be added if they improve the design.

---

## Core Use Cases
At minimum, the following use cases are expected:

- CreateUserProfile
- GetUserProfile
- UpdateUserProfile

- AddTransaction
- UpdateTransaction
- DeleteTransaction
- GetTransactions

- CreateCategory
- UpdateCategory
- DeleteCategory
- GetCategories

- CreateBudget
- UpdateBudget
- GetBudgets

- CreateSavingsGoal
- UpdateSavingsGoal
- AddContributionToGoal

- GetDashboardSummary
- GenerateFinancialInsights

- SaveUserPreferences
- GetUserPreferences

Each use case must:
- have a single responsibility
- be testable
- avoid framework-specific dependencies

---

## Constraints
The following constraints are mandatory:

- no business logic in UI
- Domain must not depend on Android classes
- Data must not depend on Presentation
- avoid unnecessary abstractions
- avoid duplicated logic
- use explicit mappers where needed
- keep naming clear and professional
- keep the codebase modular and maintainable

---

## Out of Scope for MVP
The following are intentionally excluded from the initial implementation:

- real backend
- cloud sync
- bank integration
- online authentication
- advanced AI features
- multi-device sync
- PDF/Excel export

The architecture must remain ready for these future extensions.

---

## Quality Bar
This project must aim for senior-level engineering quality:

- real Clean Architecture
- coherent folder structure
- small focused files
- reusable UI components
- testable business logic
- consistent naming
- scalable foundation

---

## Delivery Approach
The project must be built incrementally in phases.

Each phase must:
- focus only on its assigned scope
- avoid jumping into future phases
- remain consistent with previous decisions
- keep the codebase coherent

The phases are:

1. Architecture and structure
2. Project bootstrap
3. Domain layer
4. Data layer
5. Use cases
6. Presentation foundation
7. UI screens and components
8. Validation and testing