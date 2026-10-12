package org.josedev.house_control.presentation.repository

sealed interface LoginEvent {
    data class Login(val username: String, val password: String) : LoginEvent
    object SignOut : LoginEvent
}