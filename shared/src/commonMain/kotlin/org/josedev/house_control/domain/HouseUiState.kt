package org.josedev.house_control.domain

import org.josedev.house_control.domain.models.Actuator

sealed interface HouseUiState {

    data object Loading : HouseUiState

    data class Success(val actuators: Map<String,List<Actuator>>): HouseUiState

    data class Failure(val throwable: Throwable): HouseUiState
}