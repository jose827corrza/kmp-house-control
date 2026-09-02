package org.josedev.house_control.client

import org.josedev.house_control.models.ActuatorRequest
import org.josedev.house_control.models.ActuatorResponse
import org.josedev.house_control.models.HouseResponse

interface ClientApi {
    suspend fun sendActuatorState(token: String, actuatorRequest: ActuatorRequest): Result<ActuatorResponse>
    suspend fun fetchHouses(token: String, ): Result<List<HouseResponse>>
    suspend fun fetchActuatorsByHouseId(token: String, houseId: String): Result<List<ActuatorResponse>>
}