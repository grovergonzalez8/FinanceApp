package com.grove.personalfinanceapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grove.personalfinanceapp.presentation.root.BootstrapScreen

@Composable
fun FinanceNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FinanceDestination.Bootstrap.route
    ) {
        composable(route = FinanceDestination.Bootstrap.route) {
            BootstrapScreen()
        }
    }
}
