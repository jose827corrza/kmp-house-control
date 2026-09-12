package org.josedev.house_control.data.client

import org.josedev.house_control.data.client.models.ActuatorRequest
import org.josedev.house_control.data.client.models.ActuatorResponse

interface ClientApi {
    suspend fun sendActuatorState(token: String, actuatorRequest: ActuatorRequest): Unit
    suspend fun fetchHouses(token: String): List<ActuatorResponse>
    suspend fun fetchActuatorsByHouseId(token: String, houseId: String): List<ActuatorResponse>
}