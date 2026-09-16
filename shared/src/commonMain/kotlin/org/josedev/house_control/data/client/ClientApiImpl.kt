package org.josedev.house_control.data.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.headers
import org.josedev.house_control.data.client.models.ActuatorRequest
import org.josedev.house_control.data.client.models.HouseDTO
import org.josedev.house_control.utils.AppLogger

class ClientApiImpl(
    private val httpClient: HttpClient,
    private val logger: AppLogger,
) : ClientApi {


    override suspend fun sendActuatorState(token: String, actuatorRequest: ActuatorRequest): Unit {
        return httpClient.post("control") {
            contentType(Json)
            setBody(actuatorRequest)
            headers {
                append(Authorization, "Bearer $token")
            }
        }.body()
    }

    override suspend fun fetchHouses(token: String): List<HouseDTO> {
        return httpClient.get("houses") {
            contentType(Json)
            bearerAuth(token)
        }.body()
    }

    /**
     * Deprecated
     */
    override suspend fun fetchActuatorsByHouseId(token: String, houseId: String): List<HouseDTO> {
        return httpClient.get("actuators") {
            contentType(Json)
            url {
                appendPathSegments(houseId)
            }
            headers {
                append(Authorization, "Bearer $token")
            }
        }.body()
    }
}