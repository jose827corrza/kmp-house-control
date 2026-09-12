package org.josedev.house_control.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.josedev.house_control.domain.HouseUiState
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.repository.HouseRepository
import org.josedev.house_control.utils.AppLogger

class HouseViewModel(
    private val houseRepository: HouseRepository,
    private val logger: AppLogger
) : ViewModel() {

    private val _uiState = MutableStateFlow<HouseUiState>(HouseUiState.Loading)
    val uiState: StateFlow<HouseUiState> = _uiState.asStateFlow()

    private val _rawActuators = MutableStateFlow<Map<String, List<Actuator>>>(emptyMap())
    private val _updatingIds = MutableStateFlow<Set<String>>(emptySet())
    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow<String?>(null)

    val newUiState: StateFlow<HouseUiState> = combine(
        _rawActuators,
        _updatingIds,
        _isLoading,
        _isError
    ) { actuators, updatingIds, isLoading, isError ->
        when {
            isLoading -> HouseUiState.Loading
            isError != null -> HouseUiState.Failure(Throwable("T"))
            else -> {
                val decoratedMap = actuators.mapValues { (_, list) ->
                    list.map { actuator ->
                        actuator.copy(isUpdating = updatingIds.contains(actuator.actuatorId))
                    }
                }
                HouseUiState.Success(actuators = decoratedMap)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HouseUiState.Loading
    )

    init {
        fetchHouses()
    }

    fun fetchHouses() {
        viewModelScope.launch {
            _isLoading.value = true

            houseRepository.fetchHouses("")
                .onSuccess { groupedActuators ->
                    _rawActuators.value = groupedActuators
                    _isLoading.value = false
                }
                .onFailure {
                    _isError.value = it.message
                    _isLoading.value = false
                }

        }
    }

    fun toggleActuatorState(actuatorId: String, newValue: Boolean) {
        viewModelScope.launch {
            _updatingIds.update { it + actuatorId }

            updateLocalActuatorState(actuatorId, newValue)

            val result = houseRepository.updateActuatorState(actuatorId, newValue)

            result.onFailure {
                updateLocalActuatorState(actuatorId, !newValue)
            }

            _updatingIds.update { it - actuatorId }
        }
    }

    private fun updateLocalActuatorState(actuatorId: String, newValue: Boolean) {
        _rawActuators.update { currentMap ->
            currentMap.mapValues { (_, list) ->
                list.map { act ->
                    if(act.actuatorId == actuatorId) act.copy(isUpdating = newValue) else act
                }
            }
        }
    }

}