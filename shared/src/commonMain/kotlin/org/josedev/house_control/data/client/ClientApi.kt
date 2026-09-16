package org.josedev.house_control.data.client

import org.josedev.house_control.data.client.models.ActuatorRequest
import org.josedev.house_control.data.client.models.HouseDTO

interface ClientApi {
    suspend fun sendActuatorState(token: String, actuatorRequest: ActuatorRequest): Unit
    suspend fun fetchHouses(token: String): List<HouseDTO>
    suspend fun fetchActuatorsByHouseId(token: String, houseId: String): List<HouseDTO>
}