package org.josedev.house_control.domain

import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House

sealed interface HouseUiState {

    data object Loading : HouseUiState

    data class Success(val actuators: Map<House,List<Actuator>>): HouseUiState

    data class Failure(val throwable: Throwable): HouseUiState
}