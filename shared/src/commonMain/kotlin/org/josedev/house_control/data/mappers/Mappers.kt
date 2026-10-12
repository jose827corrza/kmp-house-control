package org.josedev.house_control.data.mappers

import org.josedev.house_control.data.client.models.ActuatorDTO
import org.josedev.house_control.data.client.models.HouseDTO
import org.josedev.house_control.data.client.models.auth.AuthenticationResultDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenResponseDTO
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House
import org.josedev.house_control.domain.models.auth.AuthenticationResult
import org.josedev.house_control.domain.models.auth.CognitoTokenResponse

fun HouseDTO.toDomain(): House {
    return House(
        houseId = this.houseId,
        houseName = this.houseName,
        actuators = this.actuators.mapValues { (key, dto) -> dto.toDomain(key) },
    )
}

fun ActuatorDTO.toDomain(key: String): Actuator {
    return Actuator(
        id = key,
        name = this.name,
        isActive = this.isActive,
        isUpdating = false
    )
}

fun AuthenticationResultDTO.toDomain(): AuthenticationResult {
    return AuthenticationResult(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        tokenType = this.tokenType,
        expiresIn = this.expiresIn,
        idToken = this.idToken,
    )
}

fun CognitoTokenResponseDTO.toDomain(): CognitoTokenResponse {
    return CognitoTokenResponse(
        authenticationResult = this.authenticationResult.toDomain(),
        challengeParameters = this.challengeParameters
    )
}