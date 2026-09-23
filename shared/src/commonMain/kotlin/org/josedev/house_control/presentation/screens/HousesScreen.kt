package org.josedev.house_control.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.josedev.house_control.domain.HouseUiState
import org.josedev.house_control.presentation.components.GroupedHouseList
import org.josedev.house_control.presentation.viewmodels.HouseViewModel
import org.koin.compose.koinInject

@Composable
fun HousesScreen(
    viewModel: HouseViewModel = koinInject(),
) {
    val uiState by viewModel.newUiState.collectAsState()

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer).safeContentPadding().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        when (val state = uiState) {
            is HouseUiState.Failure -> {
                Text("Error Fetching House List")
            }

            HouseUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is HouseUiState.Success -> {
                GroupedHouseList(state.actuators, onToggleActuator = viewModel::toggleActuatorState)
            }
        }
    }
}