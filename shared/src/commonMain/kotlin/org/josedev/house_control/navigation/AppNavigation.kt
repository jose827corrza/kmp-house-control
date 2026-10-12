package org.josedev.house_control.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.josedev.house_control.navigation.routes.AppRoute
import org.josedev.house_control.presentation.screens.HousesScreen
import org.josedev.house_control.presentation.screens.LoginScreen

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()

    NavHost(navController = appNavController, startDestination = AppRoute.HomeScreen().route) {
//        composable(AppRoute.Login().route) {
//            LoginScreen(appNavController)
//        }
        composable(AppRoute.HomeScreen().route) {
            HousesScreen()
        }
    }
}