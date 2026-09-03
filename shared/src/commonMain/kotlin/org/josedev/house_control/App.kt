package org.josedev.house_control

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.josedev.house_control.di.appModule
import org.josedev.house_control.utils.AppLogger
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    var status by remember { mutableStateOf(false) }

    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            val appLogger: AppLogger = koinInject()
            var showContent by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        greeting.forEach { greeting ->
                            Text(greeting)
                            HorizontalDivider()
                        }
                    }
                }

                Switch(
                    checked = status,
                    onCheckedChange = { newValue ->
                        status = newValue
                        changed(newValue, appLogger)
                    },
                )
            }
        }
    }
}

fun changed(state: Boolean, appLogger: AppLogger) {
    appLogger.d("APP", "changed $state")
}