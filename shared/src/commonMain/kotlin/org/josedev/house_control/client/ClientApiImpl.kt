package org.josedev.house_control.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.headers
import org.josedev.house_control.models.ActuatorRequest
import org.josedev.house_control.models.ActuatorResponse
import org.josedev.house_control.models.HouseResponse

class ClientApiImpl(
    private val httpClient: HttpClient,
) : ClientApi {


    override suspend fun sendActuatorState(token: String, actuatorRequest: ActuatorRequest): Result<ActuatorResponse> {
        return try {
            val response = httpClient.post("control") {
                contentType(Json)
                setBody(ActuatorRequest(actuatorId = "1", actuatorState = true))
                headers {
                    append(Authorization, "Bearer $token")
                }
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchHouses(token: String): Result<List<HouseResponse>> {
        return try {
            val response = httpClient.get("houses") {
                contentType(Json)
                headers {
                    append(Authorization, "Bearer $token")
                }
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchActuatorsByHouseId(token: String, houseId: String): Result<List<ActuatorResponse>> {
        return try {
            val response = httpClient.get("actuators") {
                contentType(Json)
                url {
                    appendPathSegments(houseId)
                }
                headers {
                    append(Authorization, "Bearer $token")
                }
            }
            return Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}