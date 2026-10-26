package org.josedev.house_control.domain

import org.josedev.house_control.domain.models.auth.CognitoTokenResponse

data class LoginUiState (
    val isLoading: Boolean = false,
    val authInfo: CognitoTokenResponse? = null,
    val errorMessage: String? = null,
)