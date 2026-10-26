package org.josedev.house_control.data.mappers

import org.josedev.house_control.data.client.models.ActuatorDTO
import org.josedev.house_control.data.client.models.ActuatorRequestDTO
import org.josedev.house_control.data.client.models.ActuatorResponseDTO
import org.josedev.house_control.data.client.models.HouseDTO
import org.josedev.house_control.data.client.models.auth.AuthenticationResultDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenResponseDTO
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.ActuatorRequest
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
        name = this.name.orEmpty(),
        isActive = this.isActive,
        isUpdating = false
    )
}

fun ActuatorRequest.toDTO(): ActuatorRequestDTO {
    return ActuatorRequestDTO(
        houseId = this.houseId,
        actuatorId = this.actuatorId,
        actuatorState = this.actuatorState
    )
}

fun ActuatorResponseDTO.toDomain(): Actuator {
//    val test = this.actuators.entries.associate { (key, dto) -> key to dto.toDomain(key) }
    return Actuator(
        // TODO
        isActive = this.actuators.values.find { true }?.isActive ?: false,
        id = this.actuators.keys.find { true }.orEmpty(),
        name = "UPDATED ACTUATOR",
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