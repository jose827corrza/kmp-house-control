package org.josedev.house_control.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.josedev.house_control.navigation.routes.AppRoute
import org.josedev.house_control.presentation.screens.HousesScreen
import org.josedev.house_control.presentation.screens.LoginScreen
import org.josedev.house_control.presentation.screens.TestScreen

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()

    NavHost(navController = appNavController, startDestination = AppRoute.Login().route) {
        composable(AppRoute.Login().route) {
            LoginScreen(appNavController)
        }
        composable(AppRoute.Test().route) {
            TestScreen(appNavController)
        }
        composable(AppRoute.HomeScreen().route) {
            HousesScreen()
        }
    }
}