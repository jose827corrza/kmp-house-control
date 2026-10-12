package org.josedev.house_control.domain.repository

import org.josedev.house_control.domain.models.House
import org.josedev.house_control.domain.models.auth.CognitoTokenResponse

interface AuthRepository {
    suspend fun loginWithCredentials(email: String, password: String): Result<CognitoTokenResponse>
}