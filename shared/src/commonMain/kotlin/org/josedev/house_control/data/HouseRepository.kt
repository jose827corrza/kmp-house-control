package org.josedev.house_control.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.josedev.house_control.client.ClientApi

class HouseRepository(
    private val api: ClientApi,
) {
    private val scope = CoroutineScope(SupervisorJob())

    init {
        initialize()
    }

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    suspend fun refresh() {
        api.fetchHouses("token")
    }
}