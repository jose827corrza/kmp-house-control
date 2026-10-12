package org.josedev.house_control.navigation.routes

sealed class AppRoute(val route: String) {
    class Login : AppRoute("login")
    class HomeScreen : AppRoute("home_screen")
}