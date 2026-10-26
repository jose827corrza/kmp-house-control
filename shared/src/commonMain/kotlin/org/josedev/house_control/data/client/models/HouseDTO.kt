package org.josedev.house_control.data.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HouseDTO(
    @SerialName("house_id")
    val houseId: String,

    @SerialName("house_name")
    val houseName: String,

    @SerialName("actuators")
    val actuators: Map<String, ActuatorDTO>,
)
