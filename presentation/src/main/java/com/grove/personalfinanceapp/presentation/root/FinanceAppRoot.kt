package com.grove.personalfinanceapp.presentation.root

import androidx.compose.runtime.Composable
import com.grove.personalfinanceapp.presentation.core.theme.PersonalFinanceTheme
import com.grove.personalfinanceapp.presentation.navigation.FinanceNavHost

@Composable
fun FinanceAppRoot() {
    PersonalFinanceTheme {
        FinanceNavHost()
    }
}
