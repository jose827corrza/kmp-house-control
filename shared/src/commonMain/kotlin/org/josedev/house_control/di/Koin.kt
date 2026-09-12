package org.josedev.house_control.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.josedev.house_control.data.client.ClientApi
import org.josedev.house_control.data.client.ClientApiImpl
import org.josedev.house_control.data.repository.HouseRepositoryImpl
import org.josedev.house_control.domain.repository.HouseRepository
import org.josedev.house_control.presentation.viewmodels.HouseViewModel
import org.josedev.house_control.utils.Constants.BASE_URL
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val loggingModule: Module

fun createHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens("e", "w")
            }
        }
    }
    defaultRequest {
        url(BASE_URL)
    }
}

val networkModule = module {
    single { createHttpClient() }
    singleOf(::ClientApiImpl) bind ClientApi::class
}

val repositoryModule = module {
    singleOf(::HouseRepositoryImpl) bind HouseRepository::class
}

val viewModelModule = module {
    viewModelOf(::HouseViewModel)
}

val appModule = module {
    includes(loggingModule, networkModule, repositoryModule, viewModelModule)
}