package org.josedev.house_control.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.josedev.house_control.auth.TokenStorage
import org.josedev.house_control.domain.LoginUiState
import org.josedev.house_control.domain.models.auth.CognitoTokenResponse
import org.josedev.house_control.domain.repository.AuthRepository
import org.josedev.house_control.presentation.repository.LoginEvent
import org.josedev.house_control.utils.AppLogger

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage,
    private val logger: AppLogger,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                _uiState.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    authRepository.loginWithCredentials(event.username, event.password)
                        .onSuccess { response ->
                            _uiState.update { x ->
                                x.copy(
                                    authInfo = response,
                                    isLoading = false,
                                    errorMessage = null,
                                )
                            }
                            saveTokens(response)
                        }
                        .onFailure { x ->
                            _uiState.update { it.copy(isLoading = false, errorMessage = x.message) }
                        }
                }
            }

            LoginEvent.SignOut -> {
                clearTokens()
            }
        }
    }

    fun saveTokens(cognitoTokenResponse: CognitoTokenResponse) {
        tokenStorage.saveAccessToken(cognitoTokenResponse.authenticationResult.accessToken)
        tokenStorage.saveRefreshToken(cognitoTokenResponse.authenticationResult.refreshToken)
    }

    fun clearTokens() {
        tokenStorage.clearTokens()
    }
}