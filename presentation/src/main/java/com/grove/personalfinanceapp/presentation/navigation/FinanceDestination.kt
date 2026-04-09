package com.grove.personalfinanceapp.presentation.navigation

sealed interface FinanceDestination {
    val route: String

    data object Bootstrap : FinanceDestination {
        override val route: String = "bootstrap"
    }
}
