package org.josedev.house_control.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.josedev.house_control.client.ClientApi
import org.josedev.house_control.client.ClientApiImpl
import org.josedev.house_control.utils.Constants.BASE_URL
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun createHttpClient() = HttpClient{
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
                BearerTokens("e","w")
            }
        }
    }
    defaultRequest{
        url(BASE_URL)
    }
}

val networkModule = module {
    single { createHttpClient() }
    singleOf(::ClientApiImpl) bind ClientApi::class
}

val appModule = module {
    includes(networkModule)
}